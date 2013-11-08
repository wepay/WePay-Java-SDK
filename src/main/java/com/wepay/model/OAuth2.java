package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class OAuth2 extends WePayResource {
	
	protected Long userId;
	protected String accessToken;
	protected String tokenType;
	protected Long expiresIn;

	public static String authorize(OAuth2Data data, String accessToken) {
		String uri = WePayResource.uiEndpoint + "/oauth2/authorize?client_id=" + Long.toString(WePay.clientId) + "&redirect_uri=" + data.redirectUri + "&scope=" + data.scope;
		uri += (data.userName != null) ? "&user_name=" + data.userName : "";
		uri += (data.userEmail != null) ? "&user_email=" + data.userEmail : "";
		return uri;
	}
	
	public static String token(OAuth2Data data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		params.put("code", data.code);
		OAuth2 response = gson.fromJson(request("/oauth2/token", params, accessToken), OAuth2.class);
		return response.accessToken;
	}
	
}
