package com.wepay.model;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.*;

import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class KYC extends WePayResource {
		
	protected Integer kycId;
	protected KYCData kycData;
	 
	public KYC(Integer kycId) {
		this.kycId = kycId;
	}
	
	public static KYC create(KYCData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = data.build();

		String response = request("/account/kyc/create", params, accessToken);
		KYC kyc = gson.fromJson(response, KYC.class);
		kyc.kycData = data;
		return kyc;
	}
	
}
