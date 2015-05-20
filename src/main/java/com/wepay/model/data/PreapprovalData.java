package com.wepay.model.data;

import com.wepay.model.data.RbitData;

import java.math.BigDecimal;

public class PreapprovalData {
	
	public Long accountId;
	public BigDecimal amount;
	public String shortDescription;
	public String period;
	public String referenceId;
	public BigDecimal appFee;
	public String feePayer;
	public String redirectUri;
	public String callbackUri;
	public String fallbackUri;
	public Boolean requireShipping;
	public BigDecimal shippingFee;
	public Boolean chargeTax;
	public String payerEmailMessage;
	public String longDescription;
	public Integer frequency;
	public Long startTime;
	public Long endTime;
	public Boolean autoRecur;
	public String mode;
	public PrefillInfoData prefillInfo;
	public String fundingSources;
	public Long paymentMethodId;
	public String paymentMethodType;
	public String currency;
	public RbitData[] payerRbits;
	public RbitData[] transactionRbits;
}
