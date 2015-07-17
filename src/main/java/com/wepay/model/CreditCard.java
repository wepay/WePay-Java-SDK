package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class CreditCard extends WePayResource {
	
	protected Long creditCardId;
	protected String state;
	protected String creditCardName;
	protected String userName;
	protected String email;
	protected Long createTime;
	protected String inputSource;
	protected String virtualTerminalMode;
	protected String referenceId;
	protected Integer expirationMonth;
	protected Integer expirationYear;
	protected String lastFour;
	protected Long[] rbits;

	public CreditCard(Long creditCardId) {
		this.creditCardId = creditCardId;
	}
	
	public static CreditCard fetch(Long creditCardId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		String response = request("/credit_card", params, accessToken);
		CreditCard cc = gson.fromJson(response, CreditCard.class);
		return cc;
	}
	
	public void authorize(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		request("/credit_card/authorize", params, accessToken);
	}
	
	public static CreditCard[] find(CreditCardFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		if (findData != null) {
			if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
			if (findData.limit != null) params.put("limit", findData.limit);
			if (findData.start != null) params.put("start", findData.start);
			if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
		}
		JSONArray results = new JSONArray(request("/credit_card/find", params, accessToken));
		CreditCard[] found = new CreditCard[results.length()];
		for (int i = 0; i < found.length; i++) {
			CreditCard cc = gson.fromJson(results.get(i).toString(), CreditCard.class);
			found[i] = cc;
		}
		return found;		
	}
	
	public void delete(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		request("/credit_card/delete", params, accessToken);
	}
	
	public Long getCreditCardId() {
		return creditCardId;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public String getState() {
		return state;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}
	
	public long getCreateTime() {
		return createTime;
	}

	public String getInputSource() {
		return inputSource;
	}

	public String getVirtualTerminalMode() {
		return virtualTerminalMode;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public Integer getExpirationMonth() {
		return expirationMonth;
	}

	public Integer getExpirationYear() {
		return expirationYear;
	}

	public String getLastFour() {
		return lastFour;
	}

	public Long[] getRbits() {
		return rbits;
	}
	
}
