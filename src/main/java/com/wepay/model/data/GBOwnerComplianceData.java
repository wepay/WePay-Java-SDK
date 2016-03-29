package com.wepay.model.data;

import org.json.*;

public class GBOwnerComplianceData {
	public Boolean isBeneficialOwner;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("is_beneficial_owner", isBeneficialOwner);
		return json;
	}
}