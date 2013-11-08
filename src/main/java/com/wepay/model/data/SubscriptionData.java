package com.wepay.model.data;

public class SubscriptionData {

	public Long subscriptionPlanId;
	public String redirectUri;
	public String callbackUri;
	public Long paymentMethodId;
	public String paymentMethodType;
	public String mode;
	public Integer quantity;
	public String referenceId;
	public PrefillInfoData prefillInfo;
	
	//Only used by Subscription.cancel()
	public String cancelReason;
	
	//Only used by Subscription.modify()
	public Boolean prorate;
	public Long transitionExpireDays;
	public Long extendTrialDays;
	
}
