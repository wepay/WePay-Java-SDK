package com.wepay.model.data;

import java.math.BigDecimal;

public class CheckoutData {

	public Long accountId;
	public String shortDescription;
	public String type;
	public BigDecimal amount;
	public String longDescription;
	public String payerEmailMessage;
	public String payeeEmailMessage;
	public String referenceId;
	public BigDecimal appFee;
	public String feePayer;
	public String redirectUri;
	public String callbackUri;
	public String fallbackUri;
	public String cancelReason;
	public String refundReason;
	public Boolean autoCapture;
	public Boolean requireShipping;
	public BigDecimal shippingFee;
	public Boolean chargeTax;
	public String mode;
	public Long preapprovalId;
	public PrefillInfoData prefillInfo;
	public String fundingSources;
	public String paymentMethodType;
	public Long paymentMethodId;
	public String currency;
}
