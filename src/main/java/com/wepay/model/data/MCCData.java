package com.wepay.model.data;

import org.json.*;

public class MCCData {
	public String mcc;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("mcc", mcc);
		return json;
	}
}