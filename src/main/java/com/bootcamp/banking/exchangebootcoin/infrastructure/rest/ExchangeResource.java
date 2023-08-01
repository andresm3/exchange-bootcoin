package com.bootcamp.banking.exchangebootcoin.infrastructure.rest;

import static org.springframework.http.HttpStatus.CREATED;

import com.bootcamp.banking.exchangebootcoin.application.ExchangeUseCases;
import com.bootcamp.banking.exchangebootcoin.domain.models.Exchange;
import com.bootcamp.banking.exchangebootcoin.domain.request.DateRequest;
import com.bootcamp.banking.exchangebootcoin.domain.request.ExchangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
public class ExchangeResource {

  @Autowired
  ExchangeUseCases exchangeUseCases;
  @PostMapping("/create")
  @ResponseStatus(CREATED)
  Mono<Exchange> createExchange(@RequestBody Exchange request){
    return exchangeUseCases.create(request);
  }

  @GetMapping("/findByDate")
  Mono<Exchange> findByDate(@RequestBody DateRequest request){

    return exchangeUseCases.findByExchangeDate(request.getExchangeDate());
  }
}
