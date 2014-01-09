package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Checkout extends WePayResource {
	
	protected Long checkoutId;
	protected String checkoutUri;
	protected String state;
	protected BigDecimal fee;
	protected BigDecimal gross;
	protected String disputeUri;
	protected String payerEmail;
	protected String payerName;
	protected AddressData shippingAddress;
	protected BigDecimal tax;
	protected BigDecimal amountRefunded;
	protected Long createTime;
	protected CheckoutData checkoutData;
	
	public Checkout(Long checkoutId) {
		this.checkoutId = checkoutId;
	}
	
	public static Checkout fetch(Long checkoutId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", checkoutId);
		String response = request("/checkout", params, accessToken);
		Checkout c = gson.fromJson(response, Checkout.class);
		CheckoutData cd = gson.fromJson(response, CheckoutData.class);
		c.checkoutData = cd;
		return c;
	}
	
	public static Checkout[] find(CheckoutFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.accountId != null) params.put("account_id", findData.accountId);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.preapprovalId != null) params.put("preapproval_id", findData.preapprovalId);
		if (findData.startTime != null) params.put("start_time", findData.startTime);
		if (findData.endTime != null) params.put("end_time", findData.endTime);
		if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
		if (findData.shippingFee != null) params.put("shipping_fee", findData.shippingFee);
		JSONArray results = new JSONArray(request("/checkout/find", params, accessToken));
		Checkout[] found = new Checkout[results.length()];
		for (int i = 0; i < found.length; i++) {
			Checkout c = gson.fromJson(results.get(i).toString(), Checkout.class);
			CheckoutData cd = gson.fromJson(results.get(i).toString(), CheckoutData.class);
			c.checkoutData = cd;
			found[i] = c;
		}
		return found;
	}
	
	public static Checkout create(CheckoutData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.accountId);
		params.put("short_description", data.shortDescription);
		params.put("type", data.type);
		params.put("amount", data.amount);
		if (data.longDescription != null) params.put("long_description", data.longDescription);
		if (data.payerEmailMessage != null) params.put("payer_email_message", data.payerEmailMessage);
		if (data.payeeEmailMessage != null) params.put("payee_email_message", data.payeeEmailMessage);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.appFee != null) params.put("app_fee", data.appFee);
		if (data.feePayer != null) params.put("fee_payer", data.feePayer);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.currency != null) params.put("currency", data.currency);
		if (data.fallbackUri != null) params.put("fallback_uri", data.fallbackUri);
		if (data.autoCapture != null) params.put("auto_capture", data.autoCapture);
		if (data.requireShipping != null) params.put("require_shipping", data.requireShipping);
		if (data.shippingFee != null) params.put("shipping_fee", data.shippingFee);
		if (data.chargeTax != null) params.put("charge_tax", data.chargeTax);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.preapprovalId != null) params.put("preapproval_id", data.preapprovalId);
		if (data.prefillInfo != null) params.put("prefill_info", PrefillInfoData.buildPrefillInfo(data.prefillInfo));
		if (data.fundingSources != null) params.put("funding_sources", data.fundingSources);
		if (data.paymentMethodId != null) params.put("payment_method_id", data.paymentMethodId);
		if (data.paymentMethodType != null) params.put("payment_method_type", data.paymentMethodType);
		Checkout c = gson.fromJson(request("/checkout/create", params, accessToken), Checkout.class);
		c.checkoutData = data;
		return c;
	}
	
	public void modify(String newCallbackUri, String accessToken) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callbackUri
		CheckoutData data = new CheckoutData();
		data.callbackUri = newCallbackUri;
		this.modify(data, accessToken);
	}
	public void modify(CheckoutData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkoutId);
		params.put("callback_uri", data.callbackUri);
		String response = request("/checkout/modify", params, accessToken);
		Checkout c = gson.fromJson(response, Checkout.class);
		CheckoutData cd = gson.fromJson(response, CheckoutData.class);
		cd.callbackUri = data.callbackUri;
		this.checkoutId = c.checkoutId;
		this.checkoutUri = c.checkoutUri;
		this.state = c.state;
		this.fee = c.fee;
		this.gross = c.gross;
		this.disputeUri = c.disputeUri;
		this.payerEmail = c.payerEmail;
		this.payerName = c.payerName;
		this.shippingAddress = c.shippingAddress;
		this.tax = c.tax;
		this.amountRefunded = c.amountRefunded;
		this.createTime = c.createTime;
		this.checkoutData = cd;
	}
	
	public void cancel(String cancelReason, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkoutId);
		params.put("cancel_reason", cancelReason);
		request("/checkout/cancel", params, accessToken);
		if (this.checkoutData != null) this.checkoutData.cancelReason = cancelReason;
	}
	
	public void refund(CheckoutRefundData refundData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkoutId);
		params.put("refund_reason", refundData.refundReason);
		if (refundData.amount != null) params.put("amount", refundData.amount);
		if (refundData.appFee != null) params.put("app_fee", refundData.appFee);
		if (refundData.payerEmailMessage != null) params.put("payer_email_message", refundData.payerEmailMessage);
		if (refundData.payeeEmailMessage != null) params.put("payee_email_message", refundData.payeeEmailMessage);
		request("/checkout/refund", params, accessToken);
		if (this.checkoutData != null) this.checkoutData.refundReason = refundData.refundReason;
	}
	
	public void capture(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkoutId);
		Checkout c = gson.fromJson((request("/checkout/capture", params, accessToken)), Checkout.class);
		this.state = c.state;
	}

	public Long getCheckoutId() {
		return checkoutId;
	}

	public Long getAccountId() {
		return checkoutData.accountId;
	}
	
	public Long getPreapprovalId() {
		return checkoutData.preapprovalId;
	}
	
	public String getState() {
		return state;
	}
	
	public String getShortDescription() {
		return checkoutData.shortDescription;
	}
	
	public String getLongDescription() {
		return checkoutData.longDescription;
	}
	
	public String getCurrency() {
		return checkoutData.currency;
	}
	
	public BigDecimal getAmount() {
		return checkoutData.amount;
	}
	
	public BigDecimal getFee() {
		return fee;
	}
	
	public BigDecimal getGross() {
		return gross;
	}
	
	public BigDecimal getAppFee() {
		return checkoutData.appFee;
	}

	public String getFeePayer() {
		return checkoutData.feePayer;
	}
	
	public String getReferenceId() {
		return checkoutData.referenceId;
	}
	
	public String getRedirectUri() {
		return checkoutData.redirectUri;
	}
	
	public String getCallbackUri() {
		return checkoutData.callbackUri;
	}
	
	public String getDisputeUri() {
		return disputeUri;
	}
	
	public String getPayerEmail() {
		return payerEmail;
	}
	
	public String getPayerName() {
		return payerName;
	}
	
	public String getCancelReason() {
		return checkoutData.cancelReason;
	}
	
	public String getRefundReason() {
		return checkoutData.refundReason;
	}

	public Boolean isAutoCapture() {
		return checkoutData.autoCapture;
	}
	
	public Boolean isRequireShipping() {
		return checkoutData.requireShipping;
	}
	
	public AddressData getShippingAddress() {
		return shippingAddress;
	}
	
	public BigDecimal getTax() {
		return tax;
	}
	
	public BigDecimal getAmountRefunded() {
		return amountRefunded;
	}
	
	public Long getCreateTime() {
		return createTime;
	}
	
	public String getCheckoutUri() {
		return checkoutUri;
	}

	public String getPayerEmailMessage() {
		return checkoutData.payerEmailMessage;
	}
	
	public String getPayeeEmailMessage() {
		return checkoutData.payeeEmailMessage;
	}

	public String getType() {
		return checkoutData.type;
	}

	public String getFallbackUri() {
		return checkoutData.fallbackUri;
	}

	public BigDecimal getShippingFee() {
		return checkoutData.shippingFee;
	}

	public String getFundingSources() {
		return checkoutData.fundingSources;
	}
	
	public String getPaymentMethodType() {
		return checkoutData.paymentMethodType;
	}
	
	public Long getPaymentMethodId() {
		return checkoutData.paymentMethodId;
	}

}
