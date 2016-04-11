package com.wepay.model.data;
import org.json.*;

public class PMCreditCardData {
	public Long id;
	public CreditCardAdditionalData data;
	
	public JSONObject toJSON() throws JSONException {
		JSONObject o = new JSONObject();
		o.put("id", id);
		if (data != null) {
			o.put("data", data.toJSON());
		}
		return o;
	}
}
