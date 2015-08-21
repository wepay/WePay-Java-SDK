package com.wepay.model.data;
import org.json.*;

public class PMPreapprovalData {
	public Long id;

	public static JSONObject buildPreapproval(PMPreapprovalData info) throws JSONException {
		JSONObject o = new JSONObject();
		o.put("id", info.id);
		return o;
	}
}
