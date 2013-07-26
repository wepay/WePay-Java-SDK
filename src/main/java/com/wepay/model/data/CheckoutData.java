package com.wepay.model.data;

import java.math.BigDecimal;

public class CheckoutData {

	public Long account_id;
	public String short_description;
	public String type;
	public BigDecimal amount;
	public String long_description;
	public String payer_email_message;
	public String payee_email_message;
	public String reference_id;
	public BigDecimal app_fee;
	public String fee_payer;
	public String redirect_uri;
	public String callback_uri;
	public String fallback_uri;
	public String cancel_reason;
	public String refund_reason;
	public Boolean auto_capture;
	public Boolean require_shipping;
	public BigDecimal shipping_fee;
	public Boolean charge_tax;
	public String mode;
	public Long preapproval_id;
	public PrefillInfoData prefill_info;
	public String funding_sources;
	public String payment_method_type;
	public Long payment_method_id;
	
}
