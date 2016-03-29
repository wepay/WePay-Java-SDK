package com.wepay.model.data;

import org.json.*;

public class CAOwnerComplianceData {
	public String socialInsuranceNumber;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("social_insurance_number", socialInsuranceNumber);
		return json;
	}
}