package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class DateOfBirthData {

	public Integer year;
	public Integer month;
	public Integer day;

	public static JSONObject buildDateOfBirth(DateOfBirthData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("year", info.year);
		o.put("month", info.month);
		o.put("day", info.day);
		return o;
	}

}
