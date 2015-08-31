package com.wepay.model.data;

import com.wepay.model.data.RbitData;
import com.wepay.model.data.deserialization.JsonDeserializeIgnore;

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
	
	@JsonDeserializeIgnore
	public RbitData[] payerRbits;
	
	@JsonDeserializeIgnore
	public RbitData[] transactionRbits;
	
	//Only used by Subscription.cancel()
	public String cancelReason;
	
	//Only used by Subscription.modify()
	public Boolean prorate;
	public Long transitionExpireDays;
	public Long extendTrialDays;
	
}
