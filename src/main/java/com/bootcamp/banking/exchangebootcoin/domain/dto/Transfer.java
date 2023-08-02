package com.bootcamp.banking.exchangebootcoin.domain.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {

  private String sender;
  private String receiver;

  private String source;
  private BigDecimal amount;

}
