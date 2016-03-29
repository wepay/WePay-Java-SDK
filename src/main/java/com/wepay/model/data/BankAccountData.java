package com.wepay.model.data;

import org.json.*;

public class BankAccountData {
	public String clientId;
	public String email;
	public String accountId;
	public CAInstitutionData caInstitution;
	public GBInstitutionData gbInstitution;
	public USInstitutionData usInstitution;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("client_id", clientId);
		json.put("email", email);
		json.put("account_id", accountId);
		if (caInstitution != null) { json.put("ca_institution", caInstitution.build()); }
		if (gbInstitution != null) { json.put("gb_institution", gbInstitution.build()); }
		if (usInstitution != null) { json.put("us_institution", usInstitution.build()); }
		return json;
	}
}