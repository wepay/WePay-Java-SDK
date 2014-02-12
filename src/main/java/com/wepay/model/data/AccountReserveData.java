package com.wepay.model.data;
import java.math.BigDecimal;

public class AccountReserveData {

	public Long accountId;
	public String currency;
	public BigDecimal reservedAmount;
	public WithdrawalsScheduleObjectData[] withdrawalsSchedule;
}
