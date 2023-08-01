package com.bootcamp.banking.exchangebootcoin.application.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class Constants {
	
	@NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class ExchangeErrorMsg {
    public static final String MONO_NOT_FOUND_MESSAGE = "Exchange not found";
    public static final String MONO_CONFLICT_MESSAGE =
        "Exchange exists for the selected date";
  }

}
