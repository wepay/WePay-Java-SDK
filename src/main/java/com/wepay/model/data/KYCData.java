package com.wepay.model.data;

import org.json.*;

public class KYCData {
	public String clientId;
	public String accountId;
	public String deviceToken;
	public IndividualKYCData individual;
	public BusinessKYCData business;
	public OrganizationKYCData organization;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("client_id", clientId);
		json.put("account_id", accountId);
		json.put("device_token", deviceToken);
		if (individual != null) { json.put("individual", individual.build()); }
		if (business != null) { json.put("business", business.build()); }
		if (organization != null) { json.put("organization", organization.build()); }

		return json;
	}
}