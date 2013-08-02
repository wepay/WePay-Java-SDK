package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Account extends WePayResource {
		
	protected Long account_id;
	protected String state;
	protected String account_uri;
	protected BigDecimal payment_limit;
	protected String verification_state;
	protected String verification_uri;
	protected AccountData accountData;
	
	public Account(Long account_id) {
		this.account_id = account_id;
	}
	
	public static Account fetch(Long account_id, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", account_id);
		String response = request("/account", params, access_token);
		Account a = gson.fromJson(response, Account.class);
		AccountData ad = gson.fromJson(response, AccountData.class);
		a.accountData = ad;
		return a;
	}
	
	public static Account[] find(AccountFindData findData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (findData.name != null) params.put("name", findData.name);
		if (findData.reference_id != null) params.put("reference_id", findData.reference_id);
		if (findData.sort_order != null) params.put("sort_order", findData.sort_order);
		JSONArray results = new JSONArray(request("/account/find", params, access_token));
		Account[] found = new Account[results.length()];
		for (int i = 0; i < found.length; i++) {
			Account a = gson.fromJson(results.get(i).toString(), Account.class);
			AccountData ad = gson.fromJson(results.get(i).toString(), AccountData.class);
			a.accountData = ad;
			found[i] = a;
		}
		return found;
	}
	
	public static Account create(AccountData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("name", data.name);
		params.put("description", data.description);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.type != null) params.put("type", data.type);
		if (data.image_uri != null) params.put("image_uri", data.image_uri);
		if (data.gaq_domains != null) params.put("gaq_domains", data.gaq_domains);
		if (data.theme_object != null) params.put("theme_object", ThemeObjectData.build_theme_object(data.theme_object));
		if (data.mcc != null) params.put("mcc", data.mcc);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		String response = request("/account/create", params, access_token);
		Account a = gson.fromJson(response, Account.class);
		a.accountData = data;
		return a;
	}
	
	public void modify(AccountData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		if (data.name != null) params.put("name", data.name);
		if (data.description != null) params.put("description", data.description);
		if (data.reference_id != null) params.put("reference_id", data.reference_id);
		if (data.image_uri != null) params.put("image_uri", data.image_uri);
		if (data.gaq_domains != null) params.put("gaq_domains", data.gaq_domains);		
		if (data.theme_object != null) params.put("theme_object", ThemeObjectData.build_theme_object(data.theme_object));
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);	
		String response = request("/account/modify", params, access_token);
		Account a = gson.fromJson(response, Account.class);
		AccountData ad = gson.fromJson(response, AccountData.class);
		ad.callback_uri = data.callback_uri;
		this.account_id = a.account_id;
		this.state = a.state;
		this.account_uri = a.account_uri;
		this.payment_limit = a.payment_limit;
		this.verification_state = a.verification_state; 
		this.verification_uri = a.verification_uri;
		this.accountData = ad;
	}
	
	public void delete(String reason, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		if (reason != null) params.put("reason", reason);
		request("/account/delete", params, access_token);
	}
	
	public AccountBalanceData balance(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		AccountBalanceData ab = gson.fromJson(request("/account/balance", params, access_token), AccountBalanceData.class);
		return ab;
	}
	
	public String add_bank(AccountAddBankData addBankData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		if (addBankData != null) {
			if (addBankData.mode != null) params.put("mode", addBankData.mode);
			if (addBankData.redirect_uri != null) params.put("redirect_uri", addBankData.redirect_uri);
		}
		JSONObject object = new JSONObject(request("/account/add_bank", params, access_token));
		return object.getString("add_bank_uri");
	}
	
	public void set_tax(AccountTaxData[] taxesData, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		JSONArray taxes = new JSONArray();
		if (taxesData != null) {
			for (int i = 0; i < taxesData.length; i++) {
				taxes.put(AccountTaxData.build_tax(taxesData[i]));
			}
		}
		params.put("taxes", taxes);
		request("/account/set_tax", params, access_token);
	}
	public void set_tax(AccountTaxData taxData, String access_token) throws JSONException, IOException, WePayException {
		//overload function for single AccountTaxData object
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		JSONArray taxes = new JSONArray();
		if (taxData != null) taxes.put(AccountTaxData.build_tax(taxData));
		params.put("taxes", taxes);
		request("/account/set_tax", params, access_token);
	}

	public AccountTaxData[] get_tax(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("account_id", this.account_id);
		JSONArray array = new JSONArray(request("/account/get_tax", params, access_token));
		AccountTaxData[] taxData = new AccountTaxData[array.length()];
		for (int i = 0; i < array.length(); i++) {
			taxData[i] = gson.fromJson(array.get(i).toString(), AccountTaxData.class);
		}
		return taxData;
	}
	
	public Long getAccount_id() {
		return account_id;
	}
	
	public String getName() {
		return accountData.name;
	}
	
	public String getState() {
		return state;
	}
	
	public String getDescription() {
		return accountData.description;
	}
	
	public String getReference_id() {
		return accountData.reference_id;
	}

	public String getAccount_uri() {
		return account_uri;
	}

	public BigDecimal getPayment_limit() {
		return payment_limit;
	}
	
	public String[] getGaq_domains() {
		return accountData.gaq_domains;
	}
	
	public ThemeObjectData getTheme_object() {
		return accountData.theme_object;
	}

	public String getVerification_state() {
		return verification_state;
	}

	public String getVerification_uri() {
		return verification_uri;
	}

	public String getType() {
		return accountData.type;
	}
	
	public String getImage_uri() {
		return accountData.image_uri;
	}

	public Integer getMcc() {
		return accountData.mcc;
	}
	
	public String getCallback_uri() {
		return accountData.callback_uri;
	}
	
}
