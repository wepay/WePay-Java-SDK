package com.wepay.model.data;

import org.json.*;

public class BusinessKYCData extends BaseOrganizationKYCData {
	public CAOrgComplianceData caEntityCompliance;
	public GBOrgComplianceData gbEntityCompliance;
	public USOrgComplianceData usEntityCompliance;

	public JSONObject build() {
		JSONObject json = super.build();
		if (caEntityCompliance != null) { json.put("ca_entity_compliance", caEntityCompliance.build()); }
		if (gbEntityCompliance != null) { json.put("gb_entity_compliance", gbEntityCompliance.build()); }
		if (usEntityCompliance != null) { json.put("us_entity_compliance", usEntityCompliance.build()); }
		return json;
	}
}