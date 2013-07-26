package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class OAuth2 extends WePayResource {
	
	protected Long user_id;
	protected String access_token;
	protected String token_type;
	protected Long expires_in;

	public static String authorize(OAuth2Data data, String access_token) {
		String uri = WePayResource.ui_endpoint + "/oauth2/authorize?client_id=" + Long.toString(WePay.client_id) + "&redirect_uri=" + data.redirect_uri + "&scope=" + data.scope;
		uri += (data.user_name != null) ? "&user_name=" + data.user_name : "";
		uri += (data.user_email != null) ? "&user_email=" + data.user_email : "";
		return uri;
	}
	
	public static String token(OAuth2Data data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		params.put("redirect_uri", data.redirect_uri);
		if (data.callback_uri != null) params.put("callback_uri", data.callback_uri);
		params.put("code", data.code);
		OAuth2 response = gson.fromJson(request("/oauth2/token", params, access_token), OAuth2.class);
		return response.access_token;
	}
	
}
