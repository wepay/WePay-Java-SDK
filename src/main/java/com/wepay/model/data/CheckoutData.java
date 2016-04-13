package com.wepay.model.data;

import com.wepay.model.data.RbitData;
import com.wepay.model.data.deserialization.JsonDeserializeIgnore;

import java.math.BigDecimal;

public class CheckoutData {
	public Long accountId;
    public String uniqueId;
	public String shortDescription;
    public String currency;
	public String type;
	public BigDecimal amount;
	public FeeData fee;
    public Boolean autoCapture;
    public HostedCheckoutData hostedCheckout;
    public String deliveryType;
    public String cancelReason;
    public String refundReason;
    
    @JsonDeserializeIgnore
	public RbitData[] payerRbits;
    
    @JsonDeserializeIgnore
	public RbitData[] transactionRbits;
	public String longDescription;
	public String referenceId;
	public String callbackUri;
    public EmailMessageData emailMessage;
    public PaymentMethodData paymentMethod;
    public PaymentErrorData paymentError;
    public NPOInformationData npoInformation;

}
