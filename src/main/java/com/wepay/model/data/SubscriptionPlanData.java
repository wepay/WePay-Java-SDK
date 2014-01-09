package com.wepay.model.data;

import java.math.BigDecimal;

public class SubscriptionPlanData {

	public Long accountId;
	public String name;
	public String shortDescription;
	public BigDecimal amount;
	public String period; 
	public BigDecimal appFee;
	public String callbackUri;
	public Integer trialLength;
	public BigDecimal setupFee;
	public String referenceId;
	public String currency;
	
	//Only used by SubscriptionPlan.modify()
	public String updateSubscriptions; 
	public Long transitionExpireDays;
	
}
