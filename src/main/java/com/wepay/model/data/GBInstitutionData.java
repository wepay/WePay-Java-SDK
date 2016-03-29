package com.wepay.model.data;

import org.json.*;

public class GBInstitutionData {
	public String name;
	public String sortCode; // Should match /^[0-9]{2}-?[0-9]{2}-?[0-9]{2}$/
	public String accountNumber;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("sort_code", sortCode);
		json.put("account_number", accountNumber);
		return json;
	}
}