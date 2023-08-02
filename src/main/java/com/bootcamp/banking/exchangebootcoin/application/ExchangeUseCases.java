package com.bootcamp.banking.exchangebootcoin.application;

import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

public interface ExchangeUseCases {

  Mono<Exchange> create(Exchange exchange);

  Mono<Exchange> findByExchangeDate(LocalDate exchangeDate);

  Mono<Boolean> transferSolesToBootcoin(String phoneUser, String phoneSeller,
      BigDecimal amount, String description);
}
