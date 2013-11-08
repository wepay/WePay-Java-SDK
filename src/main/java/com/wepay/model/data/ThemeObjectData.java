package com.wepay.model.data;

import org.json.*;

public class ThemeObjectData {
	
	public String name;
	public String primaryColor;
	public String secondaryColor;
	public String backgroundColor;
	public String buttonColor;

	public static JSONObject buildThemeObject(ThemeObjectData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("name", info.name);
		o.put("primary_color", info.primaryColor);
		o.put("secondary_color", info.secondaryColor);
		o.put("background_color", info.backgroundColor);
		o.put("button_color", info.buttonColor);
		return o;
	}
	
}
