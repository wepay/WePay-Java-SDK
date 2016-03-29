package com.wepay.model.data;

import org.json.*;

public class BaseOrganizationKYCData {
	public AccountOwnerData accountOwner;
	public String legalEntityName;
	public String primaryUrl;
	public String entityDescription;
	public AddressData address;
	public MCCData mcc;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		if (accountOwner != null) {json.put("account_owner", accountOwner.build());}
		json.put("legal_entity_name", legalEntityName);
		json.put("primary_url", primaryUrl);
		json.put("entity_description", entityDescription);
		if (address != null) {json.put("address", address.build());}
		if (mcc != null) {json.put("mcc", mcc.build());}
		return json;
	}
}