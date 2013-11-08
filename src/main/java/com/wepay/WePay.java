package com.wepay;

import com.wepay.net.WePayResource;

public class WePay {
	
	public static Long clientId;
	public static String clientSecret;
	
	public static void initialize(Long appClientId, String appClientSecret, Boolean useStageEnv) {
		clientId = appClientId;
		clientSecret = appClientSecret;
		WePayResource.initializeWePayResource(useStageEnv);
	}

}
