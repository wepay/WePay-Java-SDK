package com.wepay.model.data;

import org.json.*;

public class IndividualKYCData {
	public AccountOwnerData accountOwner;
	public MCCData mcc;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		if (accountOwner != null) {json.put("account_owner", accountOwner.build());}
		if (mcc != null) {json.put("mcc", mcc.build());}
		return json;
	}
}