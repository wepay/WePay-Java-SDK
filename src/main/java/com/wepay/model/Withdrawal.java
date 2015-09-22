package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Withdrawal extends WePayResource {

	protected Long withdrawalId;
	protected String state;
	protected BigDecimal amount;
	protected String type;
	protected WithdrawalData withdrawalData;
	protected BankData bankData;
	protected CheckData checkData;
	
	public Withdrawal(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}
	
	public static Withdrawal fetch(Long withdrawalId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("withdrawal_id", withdrawalId);
		String response = request("/withdrawal", params, accessToken);
		Withdrawal w = gson.fromJson(response, Withdrawal.class);
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
			found[i] = w;
		}
		return found;	
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
		this.withdrawalId = w.withdrawalId;
		this.state = w.state;
		this.amount = w.amount;
		this.type = w.type;
	}

	public Long getWithdrawalId() {
		return withdrawalId;
	}
	
	public String getState() {
		return state;
	}
    
	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getType() {
		return type;
	}

	public BankData getBankData() {
		return bankData;
	}

	public CheckData getCheckData() {
		return checkData;
	}

	public WithdrawalData getWithdrawalData() {
		return withdrawalData;
	}
	
}
