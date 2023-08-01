package com.bootcamp.banking.exchangebootcoin.infrastructure.repository;

import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import java.time.LocalDate;
import reactor.core.publisher.Mono;

public interface CustomExchangeRepository {
	
	 Mono<Exchange>  findByExchangeDate(LocalDate exchangeDate);

}
