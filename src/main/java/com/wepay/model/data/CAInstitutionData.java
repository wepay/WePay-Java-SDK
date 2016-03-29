package com.wepay.model.data;

import org.json.*;

public class CAInstitutionData {
	public String name;
	public String transitNumber;
	public String institutionNumber;
	public String accountNumber;
	public String accountType; // Valid values are 'checking', 'savings'

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("transit_number", transitNumber);
		json.put("institution_number", institutionNumber);
		json.put("account_number", accountNumber);
		json.put("account_type", accountType);
		return json;
	}
	
}