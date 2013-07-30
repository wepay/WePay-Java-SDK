package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Withdrawal extends WePayResource {

	public Long withdrawal_id;
	public String withdrawal_uri;
	public String state;
	public BigDecimal amount;
	public Boolean recipient_confirmed;
	public String type;
	public Long create_time;
	public WithdrawalData withdrawalData;
	
	public Withdrawal(Long withdrawal_id) {
		this.withdrawal_id = withdrawal_id;
	}
	
	public static Withdrawal fetch(Long withdrawal_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("withdrawal_id", withdrawal_id);
		String response = request("/withdrawal", params, access_token);
		WithdrawalData wd = gson.fromJson(response, WithdrawalData.class);
		Withdrawal w = gson.fromJson(response, Withdrawal.class);
		w.withdrawalData = wd;
		return w;
	}
	
	public static Withdrawal[] find(WithdrawalFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.account_id != null) params.put("account_id", findData.account_id);
		if (findData.limit != null) params.put("limit", findData.limit);
		if (findData.start != null) params.put("start", findData.start);
		if (findData.sort_order != null) params.put("sort_order", findData.sort_order);
		JSONArray results = new JSONArray(request("/withdrawal/find", params, access_token));
		Withdrawal[] found = new Withdrawal[results.length()];
		for (int i = 0; i < found.length; i++) {
			Withdrawal w = gson.fromJson(results.get(i).toString(), Withdrawal.class);
			WithdrawalData wd = gson.fromJson(results.get(i).toString(), WithdrawalData.class);
			w.withdrawalData = wd;
			found[i] = w;
		}
		return found;	
	}
	
	public static Withdrawal create(WithdrawalData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", data.account_id);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		if (data.note != null) params.put("note", data.note);
		if (data.mode != null) params.put("mode", data.mode);
		Withdrawal w = gson.fromJson(request("/withdrawal/create", params, access_token), Withdrawal.class);
		w.withdrawalData = data;
		return w;
	}
	
	public void modify(String new_callback_uri, String access_token) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callback_uri
		WithdrawalData data = new WithdrawalData();
		data.callback_uri = new_callback_uri;
		this.modify(data, access_token);
	}
	public void modify(WithdrawalData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("withdrawal_id", this.withdrawal_id);
		params.put("callback_uri", data.callback_uri);
		String response = request("/withdrawal/modify", params, access_token);
		Withdrawal w = gson.fromJson(response, Withdrawal.class);
		WithdrawalData wd = gson.fromJson(response, WithdrawalData.class);
		wd.callback_uri = data.callback_uri;
		this.withdrawal_id = w.withdrawal_id;
		this.withdrawal_uri = w.withdrawal_uri;
		this.state = w.state;
		this.amount = w.amount;
		this.recipient_confirmed = w.recipient_confirmed;
		this.type = w.type;
		this.create_time = w.create_time;
		this.withdrawalData = wd;
	}

	public Long getWithdrawal_id() {
		return withdrawal_id;
	}
	
	public Long getAccount_id() {
		return withdrawalData.account_id;
	}
	
	public String getState() {
		return state;
	}
	
	public String getRedirect_uri() {
		return withdrawalData.redirect_uri;
	}

	public String getWithdrawal_uri() {
		return withdrawal_uri;
	}

	public String getCallback_uri() {
		return withdrawalData.callback_uri;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getNote() {
		return withdrawalData.note;
	}
	
	public Boolean isRecipient_confirmed() {
		return recipient_confirmed;
	}

	public String getType() {
		return type;
	}

	public long getCreate_time() {
		return create_time;
	}
	
}
