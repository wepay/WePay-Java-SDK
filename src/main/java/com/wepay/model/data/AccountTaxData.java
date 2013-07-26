package com.wepay.model.data;

import java.math.BigDecimal;

import org.json.*;

public class AccountTaxData {

	public BigDecimal percent;
	public String country;
	public String state;
	public Integer zip;
	
	public static JSONObject build_tax(AccountTaxData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("percent", info.percent);
		o.put("country", info.country);
		o.put("state", info.state);
		o.put("zip", info.zip);
		return o;
	}
	
}
