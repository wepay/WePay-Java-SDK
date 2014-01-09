package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Account extends WePayResource {
		
	protected Long accountId;
	protected String state;
	protected String accountUri;
	protected BigDecimal paymentLimit;
	protected String verificationState;
	protected String verificationUri;
	protected AccountData accountData;
	
	public Account(Long accountId) {
		this.accountId = accountId;
	}
	
	public static Account fetch(Long accountId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", accountId);
		String response = request("/account", params, accessToken);
		Account a = gson.fromJson(response, Account.class);
		AccountData ad = gson.fromJson(response, AccountData.class);
		a.accountData = ad;
		return a;
	}
	
	public static Account[] find(AccountFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.name != null) params.put("name", findData.name);
		if (findData.referenceId != null) params.put("reference_id", findData.referenceId);
		if (findData.sortOrder != null) params.put("sort_order", findData.sortOrder);
		JSONArray results = new JSONArray(request("/account/find", params, accessToken));
		Account[] found = new Account[results.length()];
		for (int i = 0; i < found.length; i++) {
			Account a = gson.fromJson(results.get(i).toString(), Account.class);
			AccountData ad = gson.fromJson(results.get(i).toString(), AccountData.class);
			a.accountData = ad;
			found[i] = a;
		}
		return found;
	}
	
	public static Account create(AccountData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("name", data.name);
		params.put("description", data.description);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.type != null) params.put("type", data.type);
		if (data.imageUri != null) params.put("image_uri", data.imageUri);
		if (data.gaqDomains != null) params.put("gaq_domains", data.gaqDomains);
		if (data.themeObject != null) params.put("theme_object", ThemeObjectData.buildThemeObject(data.themeObject));
		if (data.mcc != null) params.put("mcc", data.mcc);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		if (data.country != null) params.put("country", data.country);
		if (data.currencies != null) params.put("currencies", data.currencies);
		String response = request("/account/create", params, accessToken);
		Account a = gson.fromJson(response, Account.class);
		a.accountData = data;
		return a;
	}
	
	public void modify(AccountData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		if (data.name != null) params.put("name", data.name);
		if (data.description != null) params.put("description", data.description);
		if (data.referenceId != null) params.put("reference_id", data.referenceId);
		if (data.imageUri != null) params.put("image_uri", data.imageUri);
		if (data.gaqDomains != null) params.put("gaq_domains", data.gaqDomains);		
		if (data.themeObject != null) params.put("theme_object", ThemeObjectData.buildThemeObject(data.themeObject));
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);	
		String response = request("/account/modify", params, accessToken);
		Account a = gson.fromJson(response, Account.class);
		AccountData ad = gson.fromJson(response, AccountData.class);
		ad.callbackUri = data.callbackUri;
		this.accountId = a.accountId;
		this.state = a.state;
		this.accountUri = a.accountUri;
		this.paymentLimit = a.paymentLimit;
		this.verificationState = a.verificationState; 
		this.verificationUri = a.verificationUri;
		this.accountData = ad;
	}
	
	public void delete(String reason, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		if (reason != null) params.put("reason", reason);
		request("/account/delete", params, accessToken);
	}
	
	public AccountBalanceData balance(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		AccountBalanceData ab = gson.fromJson(request("/account/balance", params, accessToken), AccountBalanceData.class);
		return ab;
	}
	
	public String addBank(AccountAddBankData addBankData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		if (addBankData != null) {
			if (addBankData.mode != null) params.put("mode", addBankData.mode);
			if (addBankData.redirectUri != null) params.put("redirect_uri", addBankData.redirectUri);
		}
		JSONObject object = new JSONObject(request("/account/add_bank", params, accessToken));
		return object.getString("add_bank_uri");
	}
	
	public void setTax(AccountTaxData[] taxesData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		JSONArray taxes = new JSONArray();
		if (taxesData != null) {
			for (int i = 0; i < taxesData.length; i++) {
				taxes.put(AccountTaxData.buildTax(taxesData[i]));
			}
		}
		params.put("taxes", taxes);
		request("/account/set_tax", params, accessToken);
	}
	public void setTax(AccountTaxData taxData, String accessToken) throws JSONException, IOException, WePayException {
		//overload function for single AccountTaxData object
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		JSONArray taxes = new JSONArray();
		if (taxData != null) taxes.put(AccountTaxData.buildTax(taxData));
		params.put("taxes", taxes);
		request("/account/set_tax", params, accessToken);
	}

	public AccountTaxData[] getTax(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.accountId);
		JSONArray array = new JSONArray(request("/account/get_tax", params, accessToken));
		AccountTaxData[] taxData = new AccountTaxData[array.length()];
		for (int i = 0; i < array.length(); i++) {
			taxData[i] = gson.fromJson(array.get(i).toString(), AccountTaxData.class);
		}
		return taxData;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public String getName() {
		return accountData.name;
	}
	
	public String getState() {
		return state;
	}
    
	public String getCountry() {
		return accountData.country;
	}

    public String[] getCurrencies() {
		return accountData.currencies;
	}
    
	public String getDescription() {
		return accountData.description;
	}
	
	public String getReferenceId() {
		return accountData.referenceId;
	}

	public String getAccountUri() {
		return accountUri;
	}

	public BigDecimal getPaymentLimit() {
		return paymentLimit;
	}
	
	public String[] getGaqDomains() {
		return accountData.gaqDomains;
	}
	
	public ThemeObjectData getThemeObject() {
		return accountData.themeObject;
	}

	public String getVerificationState() {
		return verificationState;
	}

	public String getVerificationUri() {
		return verificationUri;
	}

	public String getType() {
		return accountData.type;
	}
	
	public String getImageUri() {
		return accountData.imageUri;
	}

	public Integer getMcc() {
		return accountData.mcc;
	}
	
	public String getCallbackUri() {
		return accountData.callbackUri;
	}
	
}
