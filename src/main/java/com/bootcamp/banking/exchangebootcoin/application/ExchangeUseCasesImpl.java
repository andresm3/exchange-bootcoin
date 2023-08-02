package com.bootcamp.banking.exchangebootcoin.application;

import com.bootcamp.banking.exchangebootcoin.application.exceptions.custom.CustomInformationException;
import com.bootcamp.banking.exchangebootcoin.application.producer.KafkaStringProducer;
import com.bootcamp.banking.exchangebootcoin.application.utils.Constants;
import com.bootcamp.banking.exchangebootcoin.domain.dto.Transfer;
import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import com.bootcamp.banking.exchangebootcoin.domain.models.Wallet;
import com.bootcamp.banking.exchangebootcoin.infrastructure.repository.ExchangeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

@Service
public class ExchangeUseCasesImpl implements ExchangeUseCases{

  @Autowired
  private KafkaStringProducer kafkaStringProducer;
  @Autowired
  private ExchangeRepository exchangeRepository;

  @Autowired
  private WalletUseCases walletUseCases;

  @Override
  public Mono<Exchange> create(Exchange exchange) {
    return exchangeRepository.findByExchangeDate(exchange.getExchangeDate())
        .doOnNext( a ->Mono.error(new CustomInformationException(Constants.ExchangeErrorMsg.MONO_CONFLICT_MESSAGE)))
        .switchIfEmpty(exchangeRepository.save(exchange));
  }

  @Override
  public Mono<Exchange> findByExchangeDate(LocalDate exchangeDate) {
    return exchangeRepository.findByExchangeDate(exchangeDate)
        .switchIfEmpty(Mono.error(new CustomInformationException(Constants.ExchangeErrorMsg.MONO_NOT_FOUND_MESSAGE)));
  }

  @Override
  public Mono<Boolean> transferSolesToBootcoin(String phoneUser, String phoneSeller,
      BigDecimal amount, String description) {
    Mono<ObjectId> buyerIdAccount = walletUseCases.getAccountIdByPhone(phoneUser)
        .subscribeOn(Schedulers.parallel());
    Mono<ObjectId> sellerIdAccount = walletUseCases.getAccountIdByPhone(phoneSeller)
        .subscribeOn(Schedulers.parallel());

    Mono<Tuple2<ObjectId, ObjectId>> zip = Mono.zip(buyerIdAccount, sellerIdAccount);
    return zip
        .flatMap(res -> {
          System.out.println("Out account: {}" + res.getT1());
          System.out.println("In account: {}" + res.getT2());
          ObjectId outAccount = res.getT1();
          ObjectId inAccount = res.getT2();

          Transfer msg = Transfer.builder()
              .sender(outAccount.toString())
              .receiver(inAccount.toString())
              .amount(amount)
              .source(description)
              .build();
          this.kafkaStringProducer.sendMessage(msg);
          return Mono.just(true);
        });
  }
}
