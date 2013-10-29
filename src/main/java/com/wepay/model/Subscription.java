package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Subscription extends WePayResource {

	protected Long subscription_id;
	protected String subscription_uri;
	protected String payer_name;
	protected String payer_email;
	protected String currency;
	protected BigDecimal amount;
	protected String period;
	protected String fee_payer;
	protected BigDecimal app_fee;
	protected String state;
	protected Long create_time;
	protected Long trial_days_remaining;
	protected Long transition_expire_time;
	protected Boolean transition_prorate;
	protected Integer transition_quantity;
	protected Long transition_subscription_plan_id;
	protected SubscriptionData subscriptionData;
	
	public Subscription(Long subscription_id) {
		this.subscription_id = subscription_id;
	}
	
	public static Subscription fetch(Long subscription_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", subscription_id);
		String response = request("/subscription", params, access_token);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		s.subscriptionData = sd;
		return s;
	}
	
	public static Subscription[] find(SubscriptionFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.subscription_plan_id != null) params.put("subscription_plan_id", findData.subscription_plan_id);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.start_time != null) params.put("start_time", findData.start_time);
		if (findData.end_time != null) params.put("end_time", findData.end_time);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
		JSONArray results = new JSONArray(request("/subscription/find", params, access_token));
		Subscription[] found = new Subscription[results.length()];
		for (int i = 0; i < found.length; i++) {
			Subscription s = gson.fromJson(results.get(i).toString(), Subscription.class);
			SubscriptionData sd = gson.fromJson(results.get(i).toString(), SubscriptionData.class);
			s.subscriptionData = sd;
			found[i] = s;
		}
		return found;
	}
	
	public static Subscription create(SubscriptionData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", data.subscription_plan_id);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.payment_method_id != null) params.put("payment_method_id", data.payment_method_id);
		if (data.payment_method_type != null) params.put("payment_method_type", data.payment_method_type);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.quantity != null) params.put("quantity", data.quantity);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.prefill_info != null) params.put("prefill_info", PrefillInfoData.build_prefill_info(data.prefill_info));
		String response = request("/subscription/create", params, access_token);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		s.subscriptionData = sd;
		return s;
	}
	
	public void modify(SubscriptionData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", this.subscription_id);
		if (data.subscription_plan_id != null) params.put("subscription_plan_id", data.subscription_plan_id);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		if (data.payment_method_id != null) params.put("payment_method_id", data.payment_method_id);
		if (data.payment_method_type != null) params.put("payment_method_type", data.payment_method_type);
		if (data.quantity != null) params.put("quantity", data.quantity);
		if (data.prorate != null) params.put("prorate", data.prorate);
		if (data.transition_expire_days != null) params.put("transition_expire_days", data.transition_expire_days);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.extend_trial_days != null) params.put("extend_trial_days", data.extend_trial_days);
		String response = request("/subscription/modify", params, access_token);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		this.subscription_uri = s.subscription_uri;
		this.payer_name = s.payer_name;
		this.payer_email = s.payer_email;
		this.currency = s.currency;
		this.amount = s.amount;
		this.period = s.period;
		this.fee_payer = s.fee_payer;
		this.app_fee = s.app_fee;
		this.create_time = s.create_time;
		this.state = s.state;
		this.trial_days_remaining = s.trial_days_remaining;
		this.transition_expire_time = s.transition_expire_time;
		this.transition_prorate = s.transition_prorate;
		this.transition_quantity = s.transition_quantity;
		this.transition_subscription_plan_id = s.transition_subscription_plan_id;		
		this.subscriptionData = sd;
	}
		
	public void cancel(String reason, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", this.subscription_id);
		if (reason != null) params.put("reason", reason);
		request("/subscription/cancel", params, access_token);
		if (this.subscriptionData != null && reason != null) this.subscriptionData.cancel_reason = reason;
	}
	
	public Long getSubscriptionPlanId() {
		return subscriptionData.subscription_plan_id;
	}
	
	public Long getSubscriptionId() {
		return subscription_id;
	}
	
	public String getSubscriptionUri() {
		return subscription_uri;
	}
	
	public String getPayerName() {
		return payer_name;
	}
	
	public String getPayerEmail() {
		return payer_email;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public BigDecimal getAppFee() {
		return app_fee;
	}
	
	public String getFeePayer() {
		return fee_payer;
	}
	
	public String getState() {
		return state;
	}
	
	public Long getCreateTime() {
		return create_time;
	}
	
	public Long getTrialDaysRemaining() {
		return trial_days_remaining;
	}
	
	public Long getTransitionExpireTime() {
		return transition_expire_time;
	}
	
	public Boolean getTransitionProrate() {
		return transition_prorate;
	}
	
	public Integer getTransitionQuantity() {
		return transition_quantity;
	}
	
	public Long getTransitionSubscriptionPlanId() {
		return transition_subscription_plan_id;
	}
	
	public String getRedirectUri() {
		return subscriptionData.redirect_uri;
	}
	
	public String getCallbackUri() {
		return subscriptionData.callback_uri;
	}
	
	public Long getPaymentMethodId() {
		return subscriptionData.payment_method_id;
	}
	
	public String getPaymentMethodType() {
		return subscriptionData.payment_method_type;
	}
	
	public Integer getQuantity() {
		return subscriptionData.quantity;
	}
	
	public String getMode() {
		return subscriptionData.mode;
	}
	
	public String getReferenceId() {
		return subscriptionData.reference_id;
	}
	
}
