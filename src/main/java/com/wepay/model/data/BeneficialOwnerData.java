package com.wepay.model.data;

import org.json.*;

public class BeneficialOwnerData {
	public PersonalNameData name;
	public AddressData address;
	public GregorianDateData dateOfBirth;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		if (name != null) {json.put("name", name.build());}
		if (address != null) {json.put("address", address.build());}
		if (dateOfBirth != null) {json.put("date_of_birth", dateOfBirth.build());}
		return json;
	}
}