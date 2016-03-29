package com.wepay.model.data;

import org.json.*;

public class USOrgComplianceData {
	public String ein;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("ein", ein);
		return json;
	}
}