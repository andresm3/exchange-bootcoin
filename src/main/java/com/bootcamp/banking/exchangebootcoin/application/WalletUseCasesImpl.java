package com.bootcamp.banking.exchangebootcoin.application;

import com.bootcamp.banking.exchangebootcoin.domain.models.Wallet;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WalletUseCasesImpl implements WalletUseCases{

  @Autowired
  private WebClient.Builder webClient;

  private final String urlWallet = "http://localhost:8085/wallet";

  @Override
  public Mono<ObjectId> getAccountIdByPhone(String phone) {
    System.out.println(">>>WalletUseCases>getAccountIdByPhone>>>>>>>>>");
    return webClient
        .build()
        .get()
        .uri(urlWallet + "/phone/{phone}", phone)
        .retrieve()
        .bodyToMono(Wallet.class)
        .map(wallet -> {
          System.out.println(">>>>>wallet.getIdAccount()>> " + wallet.getIdAccount());
          return wallet.getIdAccount();
        })
        .switchIfEmpty(Mono.empty());
  }
}
