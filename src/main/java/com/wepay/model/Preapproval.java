package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Preapproval extends WePayResource {

	protected Long preapprovalId;
	protected String preapprovalUri;
	protected String state;
	protected String manageUri;
	protected AddressData shippingAddress;
	protected BigDecimal tax;
	protected String payerName;
	protected String payerEmail;
	protected Long createTime;
	protected Long nextDueTime;
	protected Long lastCheckoutId;
	protected Long lastCheckoutTime;
	protected Long[] payerRbits;
	protected Long[] transactionRbits;
	protected PreapprovalData preapprovalData;

	public Preapproval(Long preapprovalId) {
		this.preapprovalId = preapprovalId;
	}
	
	public static Preapproval fetch(Long preapprovalId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", preapprovalId);
		String response = request("/preapproval", params, accessToken);
		Preapproval p = gson.fromJson(response, Preapproval.class);
		PreapprovalData pd = gson.fromJson(response, PreapprovalData.class);
		p.preapprovalData = pd;
		return p;
	}
	
	public static Preapproval[] find(PreapprovalFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData != null) {
			if (findData.accountId != null) params.put("account_id", findData.accountId);
			if (findData.state != null) params.put("state", findData.state);
			if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
			if (findData.start != null) params.put("start", findData.start);
			if (findData.limit != null) params.put("limit", findData.limit);
			if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
			if (findData.lastCheckoutId != null) params.put("last_checkout_id", findData.lastCheckoutId);
			if (findData.shippingFee != null) params.put("shipping_fee", findData.shippingFee);
		}
		JSONArray results = new JSONArray(request("/preapproval/find", params, accessToken));
		Preapproval[] found = new Preapproval[results.length()];
		for (int i = 0; i < found.length; i++) {
			Preapproval p = gson.fromJson(results.get(i).toString(), Preapproval.class);
			PreapprovalData pd = gson.fromJson(results.get(i).toString(), PreapprovalData.class);
			p.preapprovalData = pd;
			found[i] = p;
		}
		return found;
	}
	
	public static Preapproval create(PreapprovalData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (data.accountId != null) params.put("account_id", data.accountId);
		if (data.amount != null) params.put("amount", data.amount);
		params.put("short_description", data.shortDescription);
		params.put("period", data.period);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.appFee != null) params.put("app_fee", data.appFee);
		if (data.feePayer != null) params.put("fee_payer", data.feePayer);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.currency != null) params.put("currency", data.currency);
		if (data.fallbackUri != null) params.put("fallback_uri", data.fallbackUri);
		if (data.requireShipping != null) params.put("require_shipping", data.requireShipping);
		if (data.shippingFee != null) params.put("shipping_fee", data.shippingFee);
		if (data.chargeTax != null) params.put("charge_tax", data.chargeTax);
		if (data.payerEmailMessage != null) params.put("payer_email_message", data.payerEmailMessage);
		if (data.longDescription != null) params.put("long_description", data.longDescription);
		if (data.frequency != null) params.put("frequency", data.frequency);
		if (data.startTime != null) params.put("start_time", data.startTime);
		if (data.endTime != null) params.put("end_time", data.endTime);
		if (data.autoRecur != null) params.put("auto_recur", data.autoRecur);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.prefillInfo != null) params.put("prefill_info", PrefillInfoData.buildPrefillInfo(data.prefillInfo));
		if (data.fundingSources != null) params.put("funding_sources", data.fundingSources);
		if (data.paymentMethodId != null) params.put("payment_method_id", data.paymentMethodId);
		if (data.paymentMethodType != null) params.put("payment_method_type", data.paymentMethodType);		
		
		if (data.payerRbits != null) {
			params.put("payer_rbits", new JSONArray(data.payerRbits));
		}
		if (data.transactionRbits != null) {
			params.put("transaction_rbits", new JSONArray(data.transactionRbits));
		}

		Preapproval p = gson.fromJson(request("/preapproval/create", params, accessToken), Preapproval.class);
		p.preapprovalData = data;
		return p;		
	}
	
	public void modify(String newCallbackUri, String accessToken) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callbackUri
		PreapprovalData data = new PreapprovalData();
		data.callbackUri = newCallbackUri;
		this.modify(data, accessToken);
	}
	public void modify(PreapprovalData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", this.preapprovalId);
		params.put("callback_uri", data.callbackUri);
		
		if (data.payerRbits != null) {
			params.put("payer_rbits", new JSONArray(data.payerRbits));
		}
		if (data.transactionRbits != null) {
			params.put("transaction_rbits", new JSONArray(data.transactionRbits));
		}

		String response = request("/preapproval/modify", params, accessToken);
		Preapproval p = gson.fromJson(response, Preapproval.class);
		PreapprovalData pd = gson.fromJson(response, PreapprovalData.class);
		pd.callbackUri = data.callbackUri;
		this.preapprovalId = p.preapprovalId;
		this.preapprovalUri = p.preapprovalUri;
		this.state = p.state;
		this.manageUri = p.manageUri;
		this.shippingAddress = p.shippingAddress;
		this.tax = p.tax;
		this.payerName = p.payerName;
		this.payerEmail = p.payerEmail;
		this.createTime = p.createTime;
		this.nextDueTime = p.nextDueTime;
		this.lastCheckoutId = p.lastCheckoutId;
		this.lastCheckoutTime = p.lastCheckoutTime;
		this.payerRbits = p.payerRbits;
		this.transactionRbits = p.transactionRbits;
		this.preapprovalData = pd;
	}
	
	public void cancel(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", this.preapprovalId);
		request("/preapproval/cancel", params, accessToken);
	}

	public Long getPreapprovalId() {
		return preapprovalId;
	}

	public String getPreapprovalUri() {
		return preapprovalUri;
	}

	public String getManageUri() {
		return manageUri;
	}
	
	public Long getAccountId() {
		return preapprovalData.accountId;
	}
	
	public String getShortDescription() {
		return preapprovalData.shortDescription;
	}
	
	public String getLongDescription() {
		return preapprovalData.longDescription;
	}

	public String getCurrency() {
		return preapprovalData.currency;
	}
	
	public BigDecimal getAmount() {
		return preapprovalData.amount;
	}
	
	public String getFeePayer() {
		return preapprovalData.feePayer;
	}
	
	public String getState() {
		return state;
	}
	
	public String getRedirectUri() {
		return preapprovalData.redirectUri;
	}
	
	public BigDecimal getAppFee() {
		return preapprovalData.appFee;
	}
	
	public String getPeriod() {
		return preapprovalData.period;
	}
	
	public int getFrequency() {
		return preapprovalData.frequency;
	}
	
	public Long getStartTime() {
		return preapprovalData.startTime;
	}
	
	public Long getEndTime() {
		return preapprovalData.endTime;
	}
	
	public String getReferenceId() {
		return preapprovalData.referenceId;
	}

	public String getCallbackUri() {
		return preapprovalData.callbackUri;
	}

	public AddressData getShippingAddress() {
		return shippingAddress;
	}
	
	public BigDecimal getShippingFee() {
		return preapprovalData.shippingFee;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public Boolean isAutoRecur() {
		return preapprovalData.autoRecur;
	}

	public String getPayerName() {
		return payerName;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public Long getNextDueTime() {
		return nextDueTime;
	}

	public Long getLastCheckoutId() {
		return lastCheckoutId;
	}
	
	public Long getLastCheckoutTime() {
		return lastCheckoutTime;
	}

	public String getFallbackUri() {
		return preapprovalData.fallbackUri;
	}

	public String getPayerEmailMessage() {
		return preapprovalData.payerEmailMessage;
	}
	
	public String getFundingSources() {
		return preapprovalData.fundingSources;
	}
	
	public Long getPaymentMethodId() {
		return preapprovalData.paymentMethodId;
	}
	
	public String getPaymentMethodType() {
		return preapprovalData.paymentMethodType;
	}

	public Long[] getPayerRbits() {
		return payerRbits;
	}

	public Long[] getTransactionRbits() {
		return transactionRbits;
	}

}
