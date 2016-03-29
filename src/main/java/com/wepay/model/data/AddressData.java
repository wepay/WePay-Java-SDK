package com.wepay.model.data;

import org.json.*;

public class AddressData {

	public String address1;
	public String address2;
	public String city;
	public String state;
	public String zip;
	public String country;
	public String region;
	public String postcode;
	
	public JSONObject build() {
		JSONObject o = new JSONObject();
		o.put("address1", address1);
		o.put("address2", address2);
		o.put("city", city);
		o.put("state", state);
		o.put("zip", zip);
		o.put("country", country);
		o.put("region", region);
		o.put("postcode", postcode);
		return o;
	}

}
