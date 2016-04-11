package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

import com.wepay.model.data.deserialization.JsonDeserializeIgnore;

public class CreditCardAdditionalData {
	
	@JsonDeserializeIgnore
	public String transactionToken;
	
	public String signatureUrl;
	public EmvReceiptData emvReceipt;
	
	public JSONObject toJSON() throws JSONException {
		JSONObject o = new JSONObject();
		o.put("transaction_token", transactionToken);
		return o;
	}
}
