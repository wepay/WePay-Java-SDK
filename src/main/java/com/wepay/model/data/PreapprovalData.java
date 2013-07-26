package com.wepay.model.data;

import java.math.BigDecimal;

public class PreapprovalData {
	
	public Long account_id;
	public BigDecimal amount;
	public String short_description;
	public String period;
	public String reference_id;
	public BigDecimal app_fee;
	public String fee_payer;
	public String redirect_uri;
	public String callback_uri;
	public String fallback_uri;
	public Boolean require_shipping;
	public BigDecimal shipping_fee;
	public Boolean charge_tax;
	public String payer_email_message;
	public String long_description;
	public Integer frequency;
	public Long start_time;
	public Long end_time;
	public Boolean auto_recur;
	public String mode;
	public PrefillInfoData prefill_info;
	public String funding_sources;
	public Long payment_method_id;
	public String payment_method_type;

}
