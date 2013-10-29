package com.wepay.model.data;

public class SubscriptionData {

	public Long subscription_plan_id;
	public String redirect_uri;
	public String callback_uri;
	public Long payment_method_id;
	public String payment_method_type;
	public String mode;
	public Integer quantity;
	public String reference_id;
	public PrefillInfoData prefill_info;
	
	//Only used by Subscription.cancel()
	public String cancel_reason;
	
	//Only used by Subscription.modify()
	public Boolean prorate;
	public Long transition_expire_days;
	public Long extend_trial_days;
	
}
