package com.wepay.model.data;

import org.json.*;

public class AddressData {

	public String address1;
	public String address2;
	public String city;
	public String state;
	public Integer zip;
	public String country;
	public String region;
	public String postcode;
	
	public static JSONObject buildAddress(AddressData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("address1", info.address1);
		o.put("address2", info.address2);
		o.put("city", info.city);
		o.put("state", info.state);
		o.put("zip", info.zip);
		o.put("country", info.country);
		o.put("region", info.region);
		o.put("postcode", info.postcode);
		return o;
	}

}
