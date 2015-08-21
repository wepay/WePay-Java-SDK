package com.wepay.model.data;
import org.json.*;

public class PMCreditCardData {
	public Long id;
    
	public static JSONObject buildCreditCard(PMCreditCardData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("id", info.id);
		return o;
	}
}
