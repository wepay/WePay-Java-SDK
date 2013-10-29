package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class SubscriptionCharge extends WePayResource {
	
	protected Long subscription_plan_id;
	protected Long subscription_id;
	protected Long subscription_charge_id;
	protected String type;
	protected BigDecimal amount;
	protected BigDecimal fee;
	protected BigDecimal app_fee;
	protected BigDecimal gross;
	protected Integer quantity;
	protected BigDecimal amount_refunded;
	protected BigDecimal amount_charged_back;
	protected String state;
	protected Long create_time;
	protected Long end_time;
	protected Long prorate_time;
	protected String refund_reason;
	
	public static SubscriptionCharge fetch(Long subscription_charge_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_charge_id", subscription_charge_id);
		String response = request("/subscription_charge", params, access_token);
		SubscriptionCharge sc = gson.fromJson(response, SubscriptionCharge.class);
		return sc;
	}
	
	public static SubscriptionCharge[] find(SubscriptionChargeFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_id", findData.subscription_id);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.start_time != null) params.put("start_time", findData.start_time);
		if (findData.end_time != null) params.put("end_time", findData.end_time);
		if (findData.type != null) params.put("type", findData.type);	
		if (findData.amount != null) params.put("amount", findData.amount);
		if (findData.state != null) params.put("state", findData.state);
		JSONArray results = new JSONArray(request("/subscription_charge/find", params, access_token));
		SubscriptionCharge[] found = new SubscriptionCharge[results.length()];
		for (int i = 0; i < found.length; i++) {
			SubscriptionCharge sc = gson.fromJson(results.get(i).toString(), SubscriptionCharge.class);
			found[i] = sc;
		}
		return found;
	}
	
	public void refund(String refund_reason, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("subscription_charge_id", this.subscription_charge_id);
		params.put("refund_reason", refund_reason);
		String response = request("/subscription_charge/refund", params, access_token);
		SubscriptionCharge sc = gson.fromJson(response, SubscriptionCharge.class);
		this.state = sc.state;
		this.subscription_id = sc.subscription_id;
		this.subscription_plan_id = sc.subscription_plan_id;
		this.refund_reason = refund_reason;
	}

	public Long getSubscriptionPlanId() {
		return subscription_plan_id;
	}

	public Long getSubscriptionId() {
		return subscription_id;
	}

	public Long getSubscriptionChargeId() {
		return subscription_charge_id;
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
		return app_fee;
	}

	public BigDecimal getGross() {
		return gross;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public BigDecimal getAmountRefunded() {
		return amount_refunded;
	}

	public BigDecimal getAmountChargedBack() {
		return amount_charged_back;
	}
	
	public String getState() {
		return state;
	}

	public Long getCreateTime() {
		return create_time;
	}

	public Long getEndTime() {
		return end_time;
	}

	public Long getProrateTime() {
		return prorate_time;
	}
		
	public String getRefundReason() {
		return refund_reason;
	}

}
