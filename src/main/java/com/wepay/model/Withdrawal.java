package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Withdrawal extends WePayResource {

	protected Long withdrawalId;
	protected String withdrawalUri;
	protected String state;
	protected BigDecimal amount;
	protected Boolean recipientConfirmed;
	protected String type;
	protected Long createTime;
	protected Long captureTime;
	protected WithdrawalData withdrawalData;
	
	public Withdrawal(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}
	
	public static Withdrawal fetch(Long withdrawalId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("withdrawal_id", withdrawalId);
		String response = request("/withdrawal", params, accessToken);
		WithdrawalData wd = gson.fromJson(response, WithdrawalData.class);
		Withdrawal w = gson.fromJson(response, Withdrawal.class);
		w.withdrawalData = wd;
		return w;
	}
	
	public static Withdrawal[] find(WithdrawalFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.accountId != null) params.put("account_id", findData.accountId);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
		JSONArray results = new JSONArray(request("/withdrawal/find", params, accessToken));
		Withdrawal[] found = new Withdrawal[results.length()];
		for (int i = 0; i < found.length; i++) {
			Withdrawal w = gson.fromJson(results.get(i).toString(), Withdrawal.class);
			WithdrawalData wd = gson.fromJson(results.get(i).toString(), WithdrawalData.class);
			w.withdrawalData = wd;
			found[i] = w;
		}
		return found;	
	}
	
	public static Withdrawal create(WithdrawalData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.accountId);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.fallbackUri != null) params.put("fallback_uri", data.fallbackUri);
		if (data.note != null) params.put("note", data.note);
		if (data.mode != null) params.put("mode", data.mode);
		if (data.currency != null) params.put("currency", data.currency);
		Withdrawal w = gson.fromJson(request("/withdrawal/create", params, accessToken), Withdrawal.class);
		w.withdrawalData = data;
		return w;
	}
	
	public void modify(String newCallbackUri, String accessToken) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callbackUri
		WithdrawalData data = new WithdrawalData();
		data.callbackUri = newCallbackUri;
		this.modify(data, accessToken);
	}
	public void modify(WithdrawalData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("withdrawal_id", this.withdrawalId);
		params.put("callback_uri", data.callbackUri);
		String response = request("/withdrawal/modify", params, accessToken);
		Withdrawal w = gson.fromJson(response, Withdrawal.class);
		WithdrawalData wd = gson.fromJson(response, WithdrawalData.class);
		wd.callbackUri = data.callbackUri;
		this.withdrawalId = w.withdrawalId;
		this.withdrawalUri = w.withdrawalUri;
		this.state = w.state;
		this.amount = w.amount;
		this.recipientConfirmed = w.recipientConfirmed;
		this.type = w.type;
		this.createTime = w.createTime;
		this.captureTime = w.captureTime;
		this.withdrawalData = wd;
	}

	public Long getWithdrawalId() {
		return withdrawalId;
	}
	
	public Long getAccountId() {
		return withdrawalData.accountId;
	}
	
	public String getState() {
		return state;
	}
    
	public String getCurrency() {
		return withdrawalData.currency;
	}
	
	public String getRedirectUri() {
		return withdrawalData.redirectUri;
	}

	public String getWithdrawalUri() {
		return withdrawalUri;
	}

	public String getCallbackUri() {
		return withdrawalData.callbackUri;
	}
	
	public String getFallbackUri() {
		return withdrawalData.fallbackUri;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getNote() {
		return withdrawalData.note;
	}
	
	public Boolean isRecipientConfirmed() {
		return recipientConfirmed;
	}

	public String getType() {
		return type;
	}

	public long getCreateTime() {
		return createTime;
	}
	
	public long getCaptureTime() {
		return captureTime;
	}
	
}
