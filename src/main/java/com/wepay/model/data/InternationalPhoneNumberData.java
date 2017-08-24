package com.wepay.model.data;
import org.json.*;

public class InternationalPhoneNumberData {
	public String countryCode;
	public String phoneNumber;

	public static JSONObject buildInternationalPhoneNumber(InternationalPhoneNumberData info) throws JSONException {
		JSONObject o = new JSONObject();
		if (info.countryCode != null) o.put("country_code", info.countryCode);
		if (info.phoneNumber != null) o.put("phone_number", info.phoneNumber);
		return o;
	}
}
