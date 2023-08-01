package com.bootcamp.banking.exchangebootcoin.infrastructure.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomExchangeRepositoryImpl implements CustomExchangeRepository {
	
	private final ReactiveMongoTemplate mongoTemplate;

	@Override
	public Mono<Exchange> findByExchangeDate(LocalDate exchangeDate) {
		
		Query query = new Query(where("exchangeDate").is(exchangeDate));
    return mongoTemplate.findOne(query, Exchange.class);
		
	}

}
