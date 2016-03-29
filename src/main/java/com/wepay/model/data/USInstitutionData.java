package com.wepay.model.data;

import org.json.*;

public class USInstitutionData {
	public String name;
	public String routingNumber;
	public String accountNumber;
	public String accountType; // Valid values are 'checking', 'savings'

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("routing_number", routingNumber);
		json.put("account_number", accountNumber);
		json.put("account_type", accountType);
		return json;
	}
}