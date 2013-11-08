package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Subscription extends WePayResource {

	protected Long subscriptionId;
	protected String subscriptionUri;
	protected String payerName;
	protected String payerEmail;
	protected String currency;
	protected BigDecimal amount;
	protected String period;
	protected String feePayer;
	protected BigDecimal appFee;
	protected String state;
	protected Long createTime;
	protected Long trialDaysRemaining;
	protected Long transitionExpireTime;
	protected Boolean transitionProrate;
	protected Integer transitionQuantity;
	protected Long transitionSubscriptionPlanId;
	protected SubscriptionData subscriptionData;
	
	public Subscription(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	
	public static Subscription fetch(Long subscriptionId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", subscriptionId);
		String response = request("/subscription", params, accessToken);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		s.subscriptionData = sd;
		return s;
	}
	
	public static Subscription[] find(SubscriptionFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.subscriptionPlanId != null) params.put("subscription_plan_id", findData.subscriptionPlanId);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.startTime != null) params.put("start_time", findData.startTime);
		if (findData.endTime != null) params.put("end_time", findData.endTime);
		if (findData.state != null) params.put("state", findData.state);
		if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
		JSONArray results = new JSONArray(request("/subscription/find", params, accessToken));
		Subscription[] found = new Subscription[results.length()];
		for (int i = 0; i < found.length; i++) {
			Subscription s = gson.fromJson(results.get(i).toString(), Subscription.class);
			SubscriptionData sd = gson.fromJson(results.get(i).toString(), SubscriptionData.class);
			s.subscriptionData = sd;
			found[i] = s;
		}
		return found;
	}
	
	public static Subscription create(SubscriptionData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_plan_id", data.subscriptionPlanId);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.paymentMethodId != null) params.put("payment_method_id", data.paymentMethodId);
		if (data.paymentMethodType != null) params.put("payment_method_type", data.paymentMethodType);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.quantity != null) params.put("quantity", data.quantity);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.prefillInfo != null) params.put("prefill_info", PrefillInfoData.buildPrefillInfo(data.prefillInfo));
		String response = request("/subscription/create", params, accessToken);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		s.subscriptionData = sd;
		return s;
	}
	
	public void modify(SubscriptionData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", this.subscriptionId);
		if (data.subscriptionPlanId != null) params.put("subscription_plan_id", data.subscriptionPlanId);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.paymentMethodId != null) params.put("payment_method_id", data.paymentMethodId);
		if (data.paymentMethodType != null) params.put("payment_method_type", data.paymentMethodType);
		if (data.quantity != null) params.put("quantity", data.quantity);
		if (data.prorate != null) params.put("prorate", data.prorate);
		if (data.transitionExpireDays != null) params.put("transition_expire_days", data.transitionExpireDays);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.extendTrialDays != null) params.put("extend_trial_days", data.extendTrialDays);
		String response = request("/subscription/modify", params, accessToken);
		Subscription s = gson.fromJson(response, Subscription.class);
		SubscriptionData sd = gson.fromJson(response, SubscriptionData.class);
		this.subscriptionUri = s.subscriptionUri;
		this.payerName = s.payerName;
		this.payerEmail = s.payerEmail;
		this.currency = s.currency;
		this.amount = s.amount;
		this.period = s.period;
		this.feePayer = s.feePayer;
		this.appFee = s.appFee;
		this.createTime = s.createTime;
		this.state = s.state;
		this.trialDaysRemaining = s.trialDaysRemaining;
		this.transitionExpireTime = s.transitionExpireTime;
		this.transitionProrate = s.transitionProrate;
		this.transitionQuantity = s.transitionQuantity;
		this.transitionSubscriptionPlanId = s.transitionSubscriptionPlanId;		
		this.subscriptionData = sd;
	}
		
	public void cancel(String reason, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", this.subscriptionId);
		if (reason != null) params.put("reason", reason);
		request("/subscription/cancel", params, accessToken);
		if (this.subscriptionData != null && reason != null) this.subscriptionData.cancelReason = reason;
	}
	
	public Long getSubscriptionPlanId() {
		return subscriptionData.subscriptionPlanId;
	}
	
	public Long getSubscriptionId() {
		return subscriptionId;
	}
	
	public String getSubscriptionUri() {
		return subscriptionUri;
	}
	
	public String getPayerName() {
		return payerName;
	}
	
	public String getPayerEmail() {
		return payerEmail;
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
		return appFee;
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
	
	public Long getTrialDaysRemaining() {
		return trialDaysRemaining;
	}
	
	public Long getTransitionExpireTime() {
		return transitionExpireTime;
	}
	
	public Boolean getTransitionProrate() {
		return transitionProrate;
	}
	
	public Integer getTransitionQuantity() {
		return transitionQuantity;
	}
	
	public Long getTransitionSubscriptionPlanId() {
		return transitionSubscriptionPlanId;
	}
	
	public String getRedirectUri() {
		return subscriptionData.redirectUri;
	}
	
	public String getCallbackUri() {
		return subscriptionData.callbackUri;
	}
	
	public Long getPaymentMethodId() {
		return subscriptionData.paymentMethodId;
	}
	
	public String getPaymentMethodType() {
		return subscriptionData.paymentMethodType;
	}
	
	public Integer getQuantity() {
		return subscriptionData.quantity;
	}
	
	public String getMode() {
		return subscriptionData.mode;
	}
	
	public String getReferenceId() {
		return subscriptionData.referenceId;
	}
	
}
