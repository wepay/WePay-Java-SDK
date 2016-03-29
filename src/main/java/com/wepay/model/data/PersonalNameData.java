package com.wepay.model.data;

import org.json.*;

public class PersonalNameData {
	public String first;
	public String middle;
	public String last;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("first", first);
		json.put("middle", middle);
		json.put("last", last);
		return json;
	}
}