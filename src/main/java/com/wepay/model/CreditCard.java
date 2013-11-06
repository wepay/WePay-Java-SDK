package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class CreditCard extends WePayResource {
	
	protected Long credit_card_id;
	protected String state;
	protected String credit_card_name;
	protected String user_name;
	protected String email;
	protected Long create_time;
	protected String reference_id;
	
	public CreditCard(Long credit_card_id) {
		this.credit_card_id = credit_card_id;
	}
	
	public static CreditCard fetch(Long credit_card_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", credit_card_id);
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		String response = request("/credit_card", params, access_token);
		CreditCard cc = gson.fromJson(response, CreditCard.class);
		return cc;
	}
	
	public void authorize(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.credit_card_id);
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		request("/credit_card/authorize", params, access_token);
	}
	
	public static CreditCard[] find(CreditCardFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		if (findData != null) {
			if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
			if (findData.limit != null) params.put("limit", findData.limit);
			if (findData.start != null) params.put("start", findData.start);
			if (findData.sort_order != null) params.put("sort_order", findData.sort_order);
		}
		JSONArray results = new JSONArray(request("/credit_card/find", params, access_token));
		CreditCard[] found = new CreditCard[results.length()];
		for (int i = 0; i < found.length; i++) {
			CreditCard cc = gson.fromJson(results.get(i).toString(), CreditCard.class);
			found[i] = cc;
		}
		return found;		
	}
	
	public void delete(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.credit_card_id);
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		request("/credit_card/delete", params, access_token);
	}
	
	public Long getCredit_card_id() {
		return credit_card_id;
	}

	public String getCredit_card_name() {
		return credit_card_name;
	}

	public String getState() {
		return state;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getEmail() {
		return email;
	}
	
	public long getCreate_time() {
		return create_time;
	}

	public String getReference_id() {
		return reference_id;
	}
	
}
