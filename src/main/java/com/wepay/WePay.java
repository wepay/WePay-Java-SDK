package com.wepay;

import com.wepay.net.WePayResource;

public class WePay {
	
	public static Long client_id;
	public static String client_secret;
	
	public static void initialize(Long app_client_id, String app_client_secret, Boolean use_stage_env) {
		client_id = app_client_id;
		client_secret = app_client_secret;
		WePayResource.initializeWePayResource(use_stage_env);
	}

}
