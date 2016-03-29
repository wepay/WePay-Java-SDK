package com.wepay.model.data;

import org.json.*;

public class GregorianDateData {
	public Integer year;
	public Integer month;
	public Integer day;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("year", year);
		json.put("month", month);
		json.put("day", day);
		return json;
	}
}