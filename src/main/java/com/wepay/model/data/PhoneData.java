package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class PhoneData {

	public String country_code;
	public String phone_number;

	public static JSONObject buildPhone(PhoneData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("phone_number", info.phone_number);
		o.put("country_code", info.country_code);
		return o;
	}

}
