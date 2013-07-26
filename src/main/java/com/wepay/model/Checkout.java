package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Checkout extends WePayResource {
	
	protected Long checkout_id;
	protected String checkout_uri;
	protected String state;
	protected String currency;
	protected BigDecimal fee;
	protected BigDecimal gross;
	protected String dispute_uri;
	protected String payer_email;
	protected String payer_name;
	protected AddressData shipping_address;
	protected BigDecimal tax;
	protected BigDecimal amount_refunded;
	protected Long create_time;
	protected CheckoutData checkoutData;
	
	public Checkout(Long checkout_id) {
		this.checkout_id = checkout_id;
	}
	
	public static Checkout fetch(Long checkout_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", checkout_id);
		String response = request("/checkout", params, access_token);
		Checkout c = gson.fromJson(response, Checkout.class);
		CheckoutData cd = gson.fromJson(response, CheckoutData.class);
		c.checkoutData = cd;
		return c;
	}
	
	public static Checkout[] find(CheckoutFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.account_id != null) params.put("account_id", findData.account_id);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.preapproval_id != null) params.put("preapproval_id", findData.preapproval_id);
		if (findData.start_time != null) params.put("start_time", findData.start_time);
		if (findData.end_time != null) params.put("end_time", findData.end_time);
		if (findData.sort_order != null) params.put("sort_order", findData.sort_order);
		if (findData.shipping_fee != null) params.put("shipping_fee", findData.shipping_fee);
		JSONArray results = new JSONArray(request("/checkout/find", params, access_token));
		Checkout[] found = new Checkout[results.length()];
		for (int i = 0; i < found.length; i++) {
			Checkout c = gson.fromJson(results.get(i).toString(), Checkout.class);
			CheckoutData cd = gson.fromJson(results.get(i).toString(), CheckoutData.class);
			c.checkoutData = cd;
			found[i] = c;
		}
		return found;
	}
	
	public static Checkout create(CheckoutData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.account_id);
		params.put("short_description", data.short_description);
		params.put("type", data.type);
		params.put("amount", data.amount);
		if (data.long_description != null) params.put("long_description", data.long_description);
		if (data.payer_email_message != null) params.put("payer_email_message", data.payer_email_message);
		if (data.payee_email_message != null) params.put("payee_email_message", data.payee_email_message);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.app_fee != null) params.put("app_fee", data.app_fee);
		if (data.fee_payer != null) params.put("fee_payer", data.fee_payer);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.fallback_uri != null) params.put("fallback_uri", data.fallback_uri);
		if (data.auto_capture != null) params.put("auto_capture", data.auto_capture);
		if (data.require_shipping != null) params.put("require_shipping", data.require_shipping);
		if (data.shipping_fee != null) params.put("shipping_fee", data.shipping_fee);
		if (data.charge_tax != null) params.put("charge_tax", data.charge_tax);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.preapproval_id != null) params.put("preapproval_id", data.preapproval_id);
		if (data.prefill_info != null) params.put("prefill_info", PrefillInfoData.build_prefill_info(data.prefill_info));
		if (data.funding_sources != null) params.put("funding_sources", data.funding_sources);
		if (data.payment_method_id != null) params.put("payment_method_id", data.payment_method_id);
		if (data.payment_method_type != null) params.put("payment_method_type", data.payment_method_type);
		Checkout c = gson.fromJson(request("/checkout/create", params, access_token), Checkout.class);
		c.checkoutData = data;
		return c;		
	}
	
	public void modify(String new_callback_uri, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkout_id);
		params.put("callback_uri", new_callback_uri);
		String response = request("/checkout/modify", params, access_token);
		Checkout c = gson.fromJson(response, Checkout.class);
		CheckoutData cd = gson.fromJson(response, CheckoutData.class);
		cd.callback_uri = new_callback_uri;
		this.checkout_id = c.checkout_id;
		this.checkout_uri = c.checkout_uri;
		this.state = c.state;
		this.currency = c.currency;
		this.fee = c.fee;
		this.gross = c.gross;
		this.dispute_uri = c.dispute_uri;
		this.payer_email = c.payer_email;
		this.payer_name = c.payer_name;
		this.shipping_address = c.shipping_address;
		this.tax = c.tax;
		this.amount_refunded = c.amount_refunded;
		this.create_time = c.create_time;
		this.checkoutData = cd;
	}
	
	public void cancel(String cancel_reason, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkout_id);
		params.put("cancel_reason", cancel_reason);
		request("/checkout/cancel", params, access_token);
		if (this.checkoutData != null) this.checkoutData.cancel_reason = cancel_reason;
	}
	
	public void refund(CheckoutRefundData refundData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkout_id);
		params.put("refund_reason", refundData.refund_reason);
		if (refundData.amount != null) params.put("amount", refundData.amount);
		if (refundData.app_fee != null) params.put("app_fee", refundData.app_fee);
		if (refundData.payer_email_message != null) params.put("payer_email_message", refundData.payer_email_message);
		if (refundData.payee_email_message != null) params.put("payee_email_message", refundData.payee_email_message);
		request("/checkout/refund", params, access_token);
		if (this.checkoutData != null) this.checkoutData.refund_reason = refundData.refund_reason;
	}
	
	public void capture(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("checkout_id", this.checkout_id);
		Checkout c = gson.fromJson((request("/checkout/capture", params, access_token)), Checkout.class);
		this.state = c.state;
	}

	public Long getCheckout_id() {
		return checkout_id;
	}

	public Long getAccount_id() {
		return checkoutData.account_id;
	}
	
	public Long getPreapproval_id() {
		return checkoutData.preapproval_id;
	}
	
	public String getState() {
		return state;
	}
	
	public String getShort_description() {
		return checkoutData.short_description;
	}
	
	public String getLong_description() {
		return checkoutData.long_description;
	}
	
	public String getCurrency() {
		return currency;
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
	
	public BigDecimal getApp_fee() {
		return checkoutData.app_fee;
	}

	public String getFee_payer() {
		return checkoutData.fee_payer;
	}
	
	public String getReference_id() {
		return checkoutData.reference_id;
	}
	
	public String getRedirect_uri() {
		return checkoutData.redirect_uri;
	}
	
	public String getCallback_uri() {
		return checkoutData.callback_uri;
	}
	
	public String getDispute_uri() {
		return dispute_uri;
	}
	
	public String getPayer_email() {
		return payer_email;
	}
	
	public String getPayer_name() {
		return payer_name;
	}
	
	public String getCancel_reason() {
		return checkoutData.cancel_reason;
	}
	
	public String getRefund_reason() {
		return checkoutData.refund_reason;
	}

	public Boolean isAuto_capture() {
		return checkoutData.auto_capture;
	}
	
	public Boolean isRequire_shipping() {
		return checkoutData.require_shipping;
	}
	
	public AddressData getShipping_address() {
		return shipping_address;
	}
	
	public BigDecimal getTax() {
		return tax;
	}
	
	public BigDecimal getAmount_refunded() {
		return amount_refunded;
	}
	
	public Long getCreate_time() {
		return create_time;
	}
	
	public String getCheckout_uri() {
		return checkout_uri;
	}

	public String getPayer_email_message() {
		return checkoutData.payer_email_message;
	}
	
	public String getPayee_email_message() {
		return checkoutData.payee_email_message;
	}

	public String getType() {
		return checkoutData.type;
	}

	public String getFallback_uri() {
		return checkoutData.fallback_uri;
	}

	public BigDecimal getShipping_fee() {
		return checkoutData.shipping_fee;
	}

	public String getFunding_sources() {
		return checkoutData.funding_sources;
	}
	
	public String getPayment_method_type() {
		return checkoutData.payment_method_type;
	}
	
	public Long getPayment_method_id() {
		return checkoutData.payment_method_id;
	}

}
