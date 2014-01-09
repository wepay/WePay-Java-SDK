package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class SubscriptionPlan extends WePayResource {
		
	protected Long subscriptionPlanId;
	protected Long createTime;
	protected Integer numberOfSubscriptions;
	protected String state;
	protected String feePayer;
	protected SubscriptionPlanData subscriptionPlanData;

	public SubscriptionPlan(Long subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}
	
	public static SubscriptionPlan fetch(Long subscriptionPlanId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", subscriptionPlanId);
		String response = request("/subscription_plan", params, accessToken);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		SubscriptionPlanData spd = gson.fromJson(response, SubscriptionPlanData.class);
		sp.subscriptionPlanData = spd;
		return sp;
	}
	
	public static SubscriptionPlan[] find(SubscriptionPlanFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.accountId != null) params.put("account_id", findData.accountId);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
		JSONArray results = new JSONArray(request("/subscription_plan/find", params, accessToken));
		SubscriptionPlan[] found = new SubscriptionPlan[results.length()];
		for (int i = 0; i < found.length; i++) {
			SubscriptionPlan sp = gson.fromJson(results.get(i).toString(), SubscriptionPlan.class);
			SubscriptionPlanData spd = gson.fromJson(results.get(i).toString(), SubscriptionPlanData.class);
			sp.subscriptionPlanData = spd;
			found[i] = sp;
		}
		return found;
	}
	
	public static SubscriptionPlan create(SubscriptionPlanData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.accountId);
		params.put("name", data.name);
		params.put("short_description", data.shortDescription);
		params.put("amount", data.amount);
		params.put("period", data.period);
		if (data.appFee != null) params.put("app_fee", data.appFee);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.trialLength != null) params.put("trial_length", data.trialLength);
		if (data.setupFee != null) params.put("setup_fee", data.setupFee);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.currency != null) params.put("currency", data.currency);
		String response = request("/subscription_plan/create", params, accessToken);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		sp.subscriptionPlanData = data;
		return sp;
	}
	
	public void modify(SubscriptionPlanData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", this.subscriptionPlanId);
		if (data.name != null) params.put("name", data.name);
		if (data.shortDescription != null) params.put("short_description", data.shortDescription);
		if (data.amount != null) params.put("amount", data.amount);
		if (data.appFee != null) params.put("app_fee", data.appFee);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.trialLength != null) params.put("trial_length", data.trialLength);
		if (data.setupFee != null) params.put("setup_fee", data.setupFee);
		if (data.updateSubscriptions != null) params.put("update_subscriptions", data.updateSubscriptions);
		if (data.transitionExpireDays != null) params.put("transition_expire_days", data.transitionExpireDays);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		String response = request("/subscription_plan/modify", params, accessToken);
		SubscriptionPlan sp = gson.fromJson(response, SubscriptionPlan.class);
		SubscriptionPlanData spd = gson.fromJson(response, SubscriptionPlanData.class);
		this.createTime = sp.createTime;
		this.numberOfSubscriptions = sp.numberOfSubscriptions;
		this.state = sp.state; 
		this.feePayer = sp.feePayer; 
		this.subscriptionPlanData = spd;
	}
	
	public void delete(String reason, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", this.subscriptionPlanId);
		if (reason != null) params.put("reason", reason);
		request("/subscription_plan/delete", params, accessToken);
	}
		
	public static String getButton(SubscriptionPlanButtonData buttonData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", buttonData.accountId);
		params.put("button_type", buttonData.buttonType);
		if (buttonData.subscriptionPlanId != null) params.put("subscription_plan_id", buttonData.subscriptionPlanId);
		if (buttonData.buttonText != null) params.put("button_text", buttonData.buttonText);
		if (buttonData.buttonOptions != null) params.put("button_options", ButtonOptionsData.buildButtonOptions(buttonData.buttonOptions));
		JSONObject object = new JSONObject(request("/subscription_plan/get_button", params, accessToken));
		return object.getString("subscription_plan_button_code");
	}
	
	public Long getSubscriptionPlanId() {
		return subscriptionPlanId;
	}
	
	public Long getAccountId() {
		return subscriptionPlanData.accountId;
	}
	
	public String getName() {
		return subscriptionPlanData.name;
	}
	
	public String getShortDescription() {
		return subscriptionPlanData.shortDescription;
	}
	
	public String getCurrency() {
		return subscriptionPlanData.currency;
	}
	
	public BigDecimal getAmount() {
		return subscriptionPlanData.amount;
	}
	
	public String getPeriod() {
		return subscriptionPlanData.period;
	}
	
	public BigDecimal getAppFee() {
		return subscriptionPlanData.appFee;
	}
	
	public String getFeePayer() {
		return feePayer;
	}
	
	public String getState() {
		return state;
	}
	
	public Long getCreateTime() {
		return createTime;
	}
	
	public Integer getNumberOfSubscriptions() {
		return numberOfSubscriptions;
	}
	
	public String getCallbackUri() {
		return subscriptionPlanData.callbackUri;
	}
	
	public Integer getTrialLength() {
		return subscriptionPlanData.trialLength;
	}
	
	public BigDecimal getSetupFee() {
		return subscriptionPlanData.setupFee;
	}
	
	public String getReferenceId() {
		return subscriptionPlanData.referenceId;
	}
		
}
