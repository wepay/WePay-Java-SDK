package com.wepay.model.data;
import java.math.BigDecimal;

public class AccountBalancesObjectData {

	public String currency;
	public BigDecimal balance;
	public BigDecimal incomingPendingAmount;
	public BigDecimal outgoingPendingAmount;
	public BigDecimal reservedAmount;
	public BigDecimal disputedAmount;
	public String withdrawalPeriod;
	public Long withdrawalNextTime;
	public String withdrawalBankName;	
}
