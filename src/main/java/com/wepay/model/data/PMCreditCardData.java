package com.wepay.model.data;
import org.json.*;

public class PMCreditCardData {
	public Long id;
	public CreditCardAdditionalData data;
	
	public static JSONObject buildCreditCard(PMCreditCardData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("id", info.id);
		if (info.data != null) {
			o.put("data", CreditCardAdditionalData.buildCreditCardAdditionalData(info.data));
		}
		return o;
	}
}
