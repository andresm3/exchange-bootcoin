package com.bootcamp.banking.exchangebootcoin.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRequest {
	
	@NotNull(message = "Field exchangeDate must be required")
	@JsonFormat(pattern = "dd/MM/yyyy")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate exchangeDate;
	@NotNull(message = "Field must be required")
	private BigDecimal exchangeRateSolToBc;

}
