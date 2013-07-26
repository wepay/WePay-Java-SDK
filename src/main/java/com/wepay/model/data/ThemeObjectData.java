package com.wepay.model.data;

import org.json.*;

public class ThemeObjectData {
	
	public String name;
	public String primary_color;
	public String secondary_color;
	public String background_color;
	public String button_color;

	public static JSONObject build_theme_object(ThemeObjectData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("name", info.name);
		o.put("primary_color", info.primary_color);
		o.put("secondary_color", info.secondary_color);
		o.put("background_color", info.background_color);
		o.put("button_color", info.button_color);
		return o;
	}
	
}
