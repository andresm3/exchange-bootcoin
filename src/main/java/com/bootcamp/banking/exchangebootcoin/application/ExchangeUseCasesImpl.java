package com.bootcamp.banking.exchangebootcoin.application;

import com.bootcamp.banking.exchangebootcoin.application.exceptions.custom.CustomInformationException;
import com.bootcamp.banking.exchangebootcoin.application.utils.Constants;
import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import com.bootcamp.banking.exchangebootcoin.infrastructure.repository.ExchangeRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeUseCasesImpl implements ExchangeUseCases{

  @Autowired
  private ExchangeRepository exchangeRepository;
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
}
