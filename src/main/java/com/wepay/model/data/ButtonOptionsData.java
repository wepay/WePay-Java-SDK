package com.wepay.model.data;

import org.json.*;

public class ButtonOptionsData {

	public Long group_id;
	public Boolean show_plan_price;
	public String show_plans;
	public String reference_id;
	
	public static JSONObject build_button_options(ButtonOptionsData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("group_id", info.group_id);
		o.put("show_plan_price", info.show_plan_price);
		o.put("show_plans", info.show_plans);
		o.put("reference_id", info.reference_id);
		return o;
	}
	
}
