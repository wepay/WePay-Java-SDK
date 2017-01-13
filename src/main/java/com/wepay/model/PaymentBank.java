package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class PaymentBank extends WePayResource {
	
	protected Long paymentBankId;
	protected String state;
	protected String bankName;
	protected String accountLastFour;

	public static PaymentBank fetch(Long paymentBankId, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		return PaymentBank.fetch(paymentBankId, headerData);
	}

	public static PaymentBank fetch(Long paymentBankId, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("payment_bank_id", paymentBankId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		String response = request("/payment_bank", params, headerData);
		PaymentBank pb = gson.fromJson(response, PaymentBank.class);
		return pb;
	}

	public static PaymentBank persist(Long paymentBankId, String accessToken) throws JSONException, IOException, WePayException {
		HeaderData headerData = new HeaderData();
		headerData.accessToken = accessToken;
		return PaymentBank.persist(paymentBankId, headerData);
	}

	public static PaymentBank persist(Long paymentBankId, HeaderData headerData) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("payment_bank_id", paymentBankId);
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		String response = request("/payment_bank/persist", params, headerData);
		PaymentBank pb = gson.fromJson(response, PaymentBank.class);
		return pb;
	}

	public Long getPaymentBankId() {
		return paymentBankId;
	}

	public String getBankName() {
		return bankName;
	}

	public String getState() {
		return state;
	}

	public String getAccountLastFour() {
		return accountLastFour;
	}
}
