package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class SubscriptionPlan extends WePayResource {
		
	protected Long subscription_plan_id;
	protected String currency;
	protected Long create_time;
	protected Integer number_of_subscriptions;
	protected String state;
	protected String fee_payer;
	protected SubscriptionPlanData subscriptionPlanData;

	public SubscriptionPlan(Long subscription_plan_id) {
		this.subscription_plan_id = subscription_plan_id;
	}
	
	public static SubscriptionPlan fetch(Long subscription_plan_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", subscription_plan_id);
		String response = request("/subscription_plan", params, access_token);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		SubscriptionPlanData spd = gson.fromJson(response, SubscriptionPlanData.class);
		sp.subscriptionPlanData = spd;
		return sp;
	}
	
	public static SubscriptionPlan[] find(SubscriptionPlanFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.account_id != null) params.put("account_id", findData.account_id);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
		JSONArray results = new JSONArray(request("/subscription_plan/find", params, access_token));
		SubscriptionPlan[] found = new SubscriptionPlan[results.length()];
		for (int i = 0; i < found.length; i++) {
			SubscriptionPlan sp = gson.fromJson(results.get(i).toString(), SubscriptionPlan.class);
			SubscriptionPlanData spd = gson.fromJson(results.get(i).toString(), SubscriptionPlanData.class);
			sp.subscriptionPlanData = spd;
			found[i] = sp;
		}
		return found;
	}
	
	public static SubscriptionPlan create(SubscriptionPlanData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.account_id);
		params.put("name", data.name);
		params.put("short_description", data.short_description);
		params.put("amount", data.amount);
		params.put("period", data.period);
		if (data.app_fee != null) params.put("app_fee", data.app_fee);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.trial_length != null) params.put("trial_length", data.trial_length);
		if (data.setup_fee != null) params.put("setup_fee", data.setup_fee);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		String response = request("/subscription_plan/create", params, access_token);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		sp.subscriptionPlanData = data;
		return sp;
	}
	
	public void modify(SubscriptionPlanData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", this.subscription_plan_id);
		if (data.name != null) params.put("name", data.name);
		if (data.short_description != null) params.put("short_description", data.short_description);
		if (data.amount != null) params.put("amount", data.amount);
		if (data.app_fee != null) params.put("app_fee", data.app_fee);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.trial_length != null) params.put("trial_length", data.trial_length);
		if (data.setup_fee != null) params.put("setup_fee", data.setup_fee);
		if (data.update_subscriptions != null) params.put("update_subscriptions", data.update_subscriptions);
		if (data.transition_expire_days != null) params.put("transition_expire_days", data.transition_expire_days);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		String response = request("/subscription_plan/modify", params, access_token);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		SubscriptionPlanData spd = gson.fromJson(response, SubscriptionPlanData.class);
		this.create_time = sp.create_time;
		this.currency = sp.currency;
		this.number_of_subscriptions = sp.number_of_subscriptions;
		this.state = sp.state; 
		this.fee_payer = sp.fee_payer; 
		this.subscriptionPlanData = spd;
	}
	
	public void delete(String reason, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", this.subscription_plan_id);
		if (reason != null) params.put("reason", reason);
		request("/subscription_plan/delete", params, access_token);
	}
		
	public static String get_button(SubscriptionPlanButtonData buttonData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", buttonData.account_id);
		params.put("button_type", buttonData.button_type);
		if (buttonData.subscription_plan_id != null) params.put("subscription_plan_id", buttonData.subscription_plan_id);
		if (buttonData.button_text != null) params.put("button_text", buttonData.button_text);
		if (buttonData.button_options != null) params.put("button_options", ButtonOptionsData.build_button_options(buttonData.button_options));
		JSONObject object = new JSONObject(request("/subscription_plan/get_button", params, access_token));
		return object.getString("subscription_plan_button_code");
	}
	
	public Long getSubscriptionPlanId() {
		return subscription_plan_id;
	}
	
	public Long getAccountId() {
		return subscriptionPlanData.account_id;
	}
	
	public String getName() {
		return subscriptionPlanData.name;
	}
	
	public String getShortDescription() {
		return subscriptionPlanData.short_description;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public BigDecimal getAmount() {
		return subscriptionPlanData.amount;
	}
	
	public String getPeriod() {
		return subscriptionPlanData.period;
	}
	
	public BigDecimal getAppFee() {
		return subscriptionPlanData.app_fee;
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
	
	public Integer getNumberOfSubscriptions() {
		return number_of_subscriptions;
	}
	
	public String getCallbackUri() {
		return subscriptionPlanData.callback_uri;
	}
	
	public Integer getTrialLength() {
		return subscriptionPlanData.trial_length;
	}
	
	public BigDecimal getSetupFee() {
		return subscriptionPlanData.setup_fee;
	}
	
	public String getReferenceId() {
		return subscriptionPlanData.reference_id;
	}
		
}
