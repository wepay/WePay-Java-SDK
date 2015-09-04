package com.wepay.model.data;

import org.json.*;

public class PrefillInfoData {
	
	public String name;
	public String email;
	public String phone_number;
	public String address;
	public String city;
	public String state;
	public String zip;
	public String country;
	public String region;
	public String postcode;

	public static JSONObject buildPrefillInfo(PrefillInfoData info) throws JSONException {
		JSONObject o = new JSONObject();
		if (info.name != null) o.put("name", info.name);
		if (info.email != null) o.put("email", info.email);
		if (info.phone_number != null) o.put("phone_number", info.phone_number);
		if (info.address != null) o.put("address", info.address);
		if (info.city != null) o.put("city", info.city);
		if (info.state != null) o.put("state", info.state);
		if (info.zip != null) o.put("zip", info.zip);
		if (info.country != null) o.put("country", info.country);
		if (info.region != null) o.put("region", info.region);
		if (info.postcode != null) o.put("postcode", info.postcode);
		return o;
	}
	
}
