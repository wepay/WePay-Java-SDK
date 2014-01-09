package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class SubscriptionCharge extends WePayResource {
	
	protected Long subscriptionPlanId;
	protected Long subscriptionId;
	protected Long subscriptionChargeId;
	protected String type;
	protected BigDecimal amount;
	protected BigDecimal fee;
	protected BigDecimal appFee;
	protected BigDecimal gross;
	protected Integer quantity;
	protected BigDecimal amountRefunded;
	protected BigDecimal amountChargedBack;
	protected String state;
	protected String currency;
	protected Long createTime;
	protected Long endTime;
	protected Long prorateTime;
	protected String refundReason;
	
	public static SubscriptionCharge fetch(Long subscriptionChargeId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_charge_id", subscriptionChargeId);
		String response = request("/subscription_charge", params, accessToken);
		SubscriptionCharge sc = gson.fromJson(response, SubscriptionCharge.class);
		return sc;
	}
	
	public static SubscriptionCharge[] find(SubscriptionChargeFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", findData.subscriptionId);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.startTime != null) params.put("start_time", findData.startTime);
		if (findData.endTime != null) params.put("end_time", findData.endTime);
		if (findData.type != null) params.put("type", findData.type);	
		if (findData.amount != null) params.put("amount", findData.amount);
		if (findData.state != null) params.put("state", findData.state);
		JSONArray results = new JSONArray(request("/subscription_charge/find", params, accessToken));
		SubscriptionCharge[] found = new SubscriptionCharge[results.length()];
		for (int i = 0; i < found.length; i++) {
			SubscriptionCharge sc = gson.fromJson(results.get(i).toString(), SubscriptionCharge.class);
			found[i] = sc;
		}
		return found;
	}
	
	public void refund(String refundReason, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_charge_id", this.subscriptionChargeId);
		params.put("refund_reason", refundReason);
		String response = request("/subscription_charge/refund", params, accessToken);
		SubscriptionCharge sc = gson.fromJson(response, SubscriptionCharge.class);
		this.state = sc.state;
		this.subscriptionId = sc.subscriptionId;
		this.subscriptionPlanId = sc.subscriptionPlanId;
		this.refundReason = refundReason;
	}

	public Long getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public Long getSubscriptionChargeId() {
		return subscriptionChargeId;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public BigDecimal getAppFee() {
		return appFee;
	}

	public BigDecimal getGross() {
		return gross;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public BigDecimal getAmountRefunded() {
		return amountRefunded;
	}

	public BigDecimal getAmountChargedBack() {
		return amountChargedBack;
	}
	
	public String getState() {
		return state;
	}
    
	public String getCurrency() {
		return currency;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public Long getProrateTime() {
		return prorateTime;
	}
		
	public String getRefundReason() {
		return refundReason;
	}

}
