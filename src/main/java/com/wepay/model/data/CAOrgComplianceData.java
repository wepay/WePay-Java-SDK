package com.wepay.model.data;

import org.json.*;

public class CAOrgComplianceData {
	public String gstHst;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("gst_hst", gstHst);
		return json;
	}
}