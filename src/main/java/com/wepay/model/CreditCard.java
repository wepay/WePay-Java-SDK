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
	protected String backingInstrumentName;
	protected Boolean autoUpdate;
	protected Long[] rbitIds;

	public CreditCard(Long creditCardId) {
		this.creditCardId = creditCardId;
	}
	
	public static CreditCard fetch(Long creditCardId, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		return CreditCard.fetch(creditCardId, headerData);
	}

	public static CreditCard fetch(Long creditCardId, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		String response = request("/credit_card", params, headerData);
		CreditCard cc = gson.fromJson(response, CreditCard.class);
		return cc;
	}

	public static CreditCard modify(Long creditCardId, String accessToken, Boolean autoUpdate, String callbackUri) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		return CreditCard.modify(creditCardId, headerData, autoUpdate, callbackUri);
	}

	public static CreditCard modify(Long creditCardId, HeaderData headerData, Boolean autoUpdate, String callbackUri) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		params.put("credit_card_id", creditCardId);
		if (autoUpdate != null) {
			params.put("auto_update", autoUpdate);
		}
		if (callbackUri != null) {
			params.put("callback_uri", callbackUri);
		}

		String response = request("/credit_card/modify", params, headerData);
		CreditCard cc = gson.fromJson(response, CreditCard.class);
		return cc;
	}
	
	public void authorize(String accessToken) throws JSONException, IOException, WePayException {
		this.authorize(0L, accessToken);
	}

	public void authorize(Long account_id, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		this.authorize(account_id, headerData);
	}

	public void authorize(Long account_id, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);

		if (account_id > 0) {
			params.put("account_id", account_id);
		}

		request("/credit_card/authorize", params, accessToken);
	}
	
	public static CreditCard[] find(CreditCardFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		return CreditCard.find(findData, headerData);
	}

	public static CreditCard[] find(CreditCardFindData findData, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		if (findData != null) {
			if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
			if (findData.limit != null) params.put("limit", findData.limit);
			if (findData.start != null) params.put("start", findData.start);
			if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
		}
		JSONArray results = new JSONArray(request("/credit_card/find", params, headerData));
		CreditCard[] found = new CreditCard[results.length()];
		for (int i = 0; i < found.length; i++) {
			CreditCard cc = gson.fromJson(results.get(i).toString(), CreditCard.class);
			found[i] = cc;
		}
		return found;
	}

	public void delete(String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		this.delete(headerData);
	}

	public void delete(HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		request("/credit_card/delete", params, headerData);
	}

	public void enableRecurring(AddressData address, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		this.enableRecurring(address, headerData);
	}

	public void enableRecurring(AddressData address, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("credit_card_id", this.creditCardId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		if (address != null) {
			params.put("address", AddressData.buildUnifiedAddress(address));
		}

		request("/credit_card/enable_recurring", params, headerData);
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

	public String getBackingInstrumentName() {
		return backingInstrumentName;
	}

	public Boolean getAutoUpdate() {
		return autoUpdate;
	}
	
	public Long[] getRbitIds() {
		return rbitIds;
	}
	
}
