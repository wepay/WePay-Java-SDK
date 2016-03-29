package com.wepay.model.data;

import org.json.*;

public class AccountOwnerData {
	public PersonalNameData name;
	public String email;
	public GregorianDateData dateOfBirth;
	public AddressData mailingAddress;
	public InternationalPhoneNumberData phone;
	public CAOwnerComplianceData caOwnerCompliance;
	public GBOwnerComplianceData gbOwnerCompliance;
	public USOwnerComplianceData usOwnerCompliance;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		if (name != null) { json.put("name", name.build()); }
		json.put("email", email);
		if (mailingAddress != null) { json.put("mailing_address", mailingAddress.build()); }
		if (phone != null) { json.put("phone", phone.build()); }
		if (caOwnerCompliance != null) { json.put("ca_owner_compliance", caOwnerCompliance.build()); }
		if (gbOwnerCompliance != null) { json.put("gb_owner_compliance", gbOwnerCompliance.build()); }
		if (usOwnerCompliance != null) { json.put("us_owner_compliance", usOwnerCompliance.build()); }
		return json;
	}
}