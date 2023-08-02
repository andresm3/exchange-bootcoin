package com.bootcamp.banking.exchangebootcoin.application;

import org.bson.types.ObjectId;
import reactor.core.publisher.Mono;

public interface WalletUseCases {

  Mono<ObjectId> getAccountIdByPhone(String phone);
}
