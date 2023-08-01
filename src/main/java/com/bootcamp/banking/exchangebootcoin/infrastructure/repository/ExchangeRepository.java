package com.bootcamp.banking.exchangebootcoin.infrastructure.repository;

import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends ReactiveMongoRepository<Exchange, ObjectId>,
	CustomExchangeRepository{

}
