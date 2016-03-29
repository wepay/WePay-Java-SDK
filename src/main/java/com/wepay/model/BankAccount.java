package com.wepay.model;

import java.io.IOException;
import java.util.Map;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class BankAccount extends WePayResource {
	
	protected BankAccountData bankAccountData;
	protected String bankAccountId;
	
	public BankAccount(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public static BankAccount create(BankAccountData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = data.build();

		String response = request("/bank_account/create", params, accessToken);
		BankAccount ba = gson.fromJson(response, BankAccount.class);
		ba.bankAccountData = data;
		return ba;
	}
	
}
