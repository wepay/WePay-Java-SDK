package com.wepay.model.data;

import org.json.*;

public class InternationalPhoneNumberData {
	public String countryCode;
	public String phoneNumber;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("country_code", countryCode);
		json.put("phone_number", phoneNumber);
		return json;
	}
}