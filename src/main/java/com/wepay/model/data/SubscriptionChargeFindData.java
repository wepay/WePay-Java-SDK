package com.wepay.model.data;

import java.math.BigDecimal;

public class SubscriptionChargeFindData {

	public Long subscription_id;
	public Integer start;
	public Integer limit;
	public Long start_time;
	public Long end_time;
	public String type;
	public BigDecimal amount;
	public String state;
	
}
