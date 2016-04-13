package com.wepay.model.data;
import org.json.*;

public class PMPreapprovalData {
	public Long id;

	public JSONObject toJSON() throws JSONException {
		JSONObject o = new JSONObject();
		o.put("id", id);
		return o;
	}
}
