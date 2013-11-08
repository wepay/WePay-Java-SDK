package com.wepay.model.data;

import org.json.*;

public class ButtonOptionsData {

	public Long groupId;
	public Boolean showPlanPrice;
	public String showPlans;
	public String referenceId;
	
	public static JSONObject buildButtonOptions(ButtonOptionsData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("group_id", info.groupId);
		o.put("show_plan_price", info.showPlanPrice);
		o.put("show_plans", info.showPlans);
		o.put("reference_id", info.referenceId);
		return o;
	}
	
}
