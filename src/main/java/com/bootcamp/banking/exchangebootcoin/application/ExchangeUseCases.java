package com.bootcamp.banking.exchangebootcoin.application;

import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import java.time.LocalDate;
import reactor.core.publisher.Mono;

public interface ExchangeUseCases {

  Mono<Exchange> create(Exchange exchange);

  Mono<Exchange> findByExchangeDate(LocalDate exchangeDate);
}
