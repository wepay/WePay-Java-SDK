package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Preapproval extends WePayResource {

	public Long preapproval_id;
	public String preapproval_uri;
	public String state;
	public String manage_uri;
	public String currency;
	public AddressData shipping_address;
	public BigDecimal tax;
	public String payer_name;
	public String payer_email;
	public Long create_time;
	public Long next_due_time;
	public Long last_checkout_id;
	public Long last_checkout_time;
	public PreapprovalData preapprovalData;

	public Preapproval(Long preapproval_id) {
		this.preapproval_id = preapproval_id;
	}
	
	public static Preapproval fetch(Long preapproval_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", preapproval_id);
		String response = request("/preapproval", params, access_token);
		Preapproval p = gson.fromJson(response, Preapproval.class);
		PreapprovalData pd = gson.fromJson(response, PreapprovalData.class);
		p.preapprovalData = pd;
		return p;
	}
	
	public static Preapproval[] find(PreapprovalFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.account_id != null) params.put("account_id", findData.account_id);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.sort_order != null) params.put("sort_order", findData.sort_order);
		if (findData.last_checkout_id != null) params.put("last_checkout_id", findData.last_checkout_id);
		if (findData.shipping_fee != null) params.put("shipping_fee", findData.shipping_fee);
		JSONArray results = new JSONArray(request("/preapproval/find", params, access_token));
		Preapproval[] found = new Preapproval[results.length()];
		for (int i = 0; i < found.length; i++) {
			Preapproval p = gson.fromJson(results.get(i).toString(), Preapproval.class);
			PreapprovalData pd = gson.fromJson(results.get(i).toString(), PreapprovalData.class);
			p.preapprovalData = pd;
			found[i] = p;
		}
		return found;
	}
	
	public static Preapproval create(PreapprovalData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (data.account_id != null) params.put("account_id", data.account_id);
		if (data.amount != null) params.put("amount", data.amount);
		params.put("short_description", data.short_description);
		params.put("period", data.period);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.app_fee != null) params.put("app_fee", data.app_fee);
		if (data.fee_payer != null) params.put("fee_payer", data.fee_payer);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.fallback_uri != null) params.put("fallback_uri", data.fallback_uri);
		if (data.require_shipping != null) params.put("require_shipping", data.require_shipping);
		if (data.shipping_fee != null) params.put("shipping_fee", data.shipping_fee);
		if (data.charge_tax != null) params.put("charge_tax", data.charge_tax);
		if (data.payer_email_message != null) params.put("payer_email_message", data.payer_email_message);
		if (data.long_description != null) params.put("long_description", data.long_description);
		if (data.frequency != null) params.put("frequency", data.frequency);
		if (data.start_time != null) params.put("start_time", data.start_time);
		if (data.end_time != null) params.put("end_time", data.end_time);
		if (data.auto_recur != null) params.put("auto_recur", data.auto_recur);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.prefill_info != null) params.put("prefill_info", PrefillInfoData.build_prefill_info(data.prefill_info));
		if (data.funding_sources != null) params.put("funding_sources", data.funding_sources);
		if (data.payment_method_id != null) params.put("payment_method_id", data.payment_method_id);
		if (data.payment_method_type != null) params.put("payment_method_type", data.payment_method_type);		
		Preapproval p = gson.fromJson(request("/preapproval/create", params, access_token), Preapproval.class);
		p.preapprovalData = data;
		return p;		
	}
	
	public void modify(String new_callback_uri, String access_token) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callback_uri
		PreapprovalData data = new PreapprovalData();
		data.callback_uri = new_callback_uri;
		this.modify(data, access_token);
	}
	public void modify(PreapprovalData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", this.preapproval_id);
		params.put("callback_uri", data.callback_uri);
		String response = request("/preapproval/modify", params, access_token);
		Preapproval p = gson.fromJson(response, Preapproval.class);
		PreapprovalData pd = gson.fromJson(response, PreapprovalData.class);
		pd.callback_uri = data.callback_uri;
		this.preapproval_id = p.preapproval_id;
		this.preapproval_uri = p.preapproval_uri;
		this.state = p.state;
		this.manage_uri = p.manage_uri;
		this.currency = p.currency;
		this.shipping_address = p.shipping_address;
		this.tax = p.tax;
		this.payer_name = p.payer_name;
		this.payer_email = p.payer_email;
		this.create_time = p.create_time;
		this.next_due_time = p.next_due_time;
		this.last_checkout_id = p.last_checkout_id;
		this.last_checkout_time = p.last_checkout_time;
		this.preapprovalData = pd;
	}
	
	public void cancel(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("preapproval_id", this.preapproval_id);
		request("/preapproval/cancel", params, access_token);
	}

	public Long getPreapproval_id() {
		return preapproval_id;
	}

	public String getPreapproval_uri() {
		return preapproval_uri;
	}

	public String getManage_uri() {
		return manage_uri;
	}
	
	public Long getAccount_id() {
		return preapprovalData.account_id;
	}
	
	public String getShort_description() {
		return preapprovalData.short_description;
	}
	
	public String getLong_description() {
		return preapprovalData.long_description;
	}

	public String getCurrency() {
		return currency;
	}
	
	public BigDecimal getAmount() {
		return preapprovalData.amount;
	}
	
	public String getFee_payer() {
		return preapprovalData.fee_payer;
	}
	
	public String getState() {
		return state;
	}
	
	public String getRedirect_uri() {
		return preapprovalData.redirect_uri;
	}
	
	public BigDecimal getApp_fee() {
		return preapprovalData.app_fee;
	}
	
	public String getPeriod() {
		return preapprovalData.period;
	}
	
	public int getFrequency() {
		return preapprovalData.frequency;
	}
	
	public Long getStart_time() {
		return preapprovalData.start_time;
	}
	
	public Long getEnd_time() {
		return preapprovalData.end_time;
	}
	
	public String getReference_id() {
		return preapprovalData.reference_id;
	}

	public String getCallback_uri() {
		return preapprovalData.callback_uri;
	}

	public AddressData getShipping_address() {
		return shipping_address;
	}
	
	public BigDecimal getShipping_fee() {
		return preapprovalData.shipping_fee;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public Boolean isAuto_recur() {
		return preapprovalData.auto_recur;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public String getPayer_email() {
		return payer_email;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public Long getNext_due_time() {
		return next_due_time;
	}

	public Long getLast_checkout_id() {
		return last_checkout_id;
	}
	
	public Long getLast_checkout_time() {
		return last_checkout_time;
	}

	public String getFallback_uri() {
		return preapprovalData.fallback_uri;
	}

	public String getPayer_email_message() {
		return preapprovalData.payer_email_message;
	}
	
	public String getFunding_sources() {
		return preapprovalData.funding_sources;
	}
	
	public Long getPayment_method_id() {
		return preapprovalData.payment_method_id;
	}
	
	public String getPayment_method_type() {
		return preapprovalData.payment_method_type;
	}

}
