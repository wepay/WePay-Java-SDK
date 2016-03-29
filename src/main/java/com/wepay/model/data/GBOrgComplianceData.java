package com.wepay.model.data;

import org.json.*;

public class GBOrgComplianceData {
	public String companyNumber;
	public String legalForm;
	public BeneficialOwnerData[] additionalBeneficialOwners;

	public JSONObject build() {
		JSONObject json = new JSONObject();
		json.put("company_number", companyNumber);
		json.put("legal_form", legalForm);
		JSONArray beneficialOwners = new JSONArray();
		if (additionalBeneficialOwners != null) {
			for (BeneficialOwnerData data : additionalBeneficialOwners) {
				beneficialOwners.put(data.build());
			}
		}
		json.put("additional_beneficial_owners", beneficialOwners);
		return json;
	}
	

}