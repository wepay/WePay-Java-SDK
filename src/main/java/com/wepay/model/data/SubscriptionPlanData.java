package com.wepay.model.data;

import java.math.BigDecimal;

public class SubscriptionPlanData {

	public Long account_id;
	public String name;
	public String short_description;
	public BigDecimal amount;
	public String period; 
	public BigDecimal app_fee;
	public String callback_uri;
	public Integer trial_length;
	public BigDecimal setup_fee;
	public String reference_id;
	
	//Only used by SubscriptionPlan.modify()
	public String update_subscriptions; 
	public Long transition_expire_days;
	
}
