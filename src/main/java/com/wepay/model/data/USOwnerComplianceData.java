package com.wepay.model.data;

import org.json.*;

public class USOwnerComplianceData {
	public String socialSecurityNumber;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("social_security_number", socialSecurityNumber);
		return json;
	}
}