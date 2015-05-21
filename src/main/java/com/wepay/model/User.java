package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class User extends WePayResource {
	
	protected Long userId;
	protected String userName;
	protected String state;
	protected String accessToken;
	protected String tokenType;
	protected Long expiresIn;
	protected Long[] rbits;
	protected UserData userData;
	
	public static User fetch(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		String response = request("/user", params, accessToken);
		User u = gson.fromJson(response, User.class);
		UserData ud = gson.fromJson(response, UserData.class);
		u.userData = ud;
		return u;
	}
	
	public void modify(String newCallbackUri, String accessToken) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callbackUri
		UserData data = new UserData();
		data.callbackUri = newCallbackUri;
		this.modify(data, accessToken);
	}
	public void modify(UserData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("callback_uri", data.callbackUri);

		if (data.rbits != null) {
			params.put("rbits", new JSONArray(data.rbits));
		}

		String response = request("/user/modify", params, accessToken);
		User u = gson.fromJson(response, User.class);
		UserData ud = gson.fromJson(response, UserData.class);
		ud.callbackUri = data.callbackUri;
		this.userId = u.userId;
		this.userName = u.userName;
		this.state = u.state;
		this.accessToken = u.accessToken;
		this.tokenType = u.tokenType;
		this.expiresIn = u.expiresIn;
		this.rbits = u.rbits;
		this.userData = ud;
	}
	
	public static User register(UserData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		params.put("email", data.email);
		params.put("scope", data.scope);
		params.put("first_name", data.firstName);
		params.put("last_name", data.lastName);
		params.put("original_ip", data.originalIp);
		params.put("original_device", data.originalDevice);
		if (data.redirectUri != null) params.put("redirect_uri", data.redirectUri);
		if (data.callbackUri != null) params.put("callback_uri", data.callbackUri);
		
		if (data.rbits != null) {
			params.put("rbits", new JSONArray(data.rbits));
		}

		User u = gson.fromJson(request("/user/register", params, accessToken), User.class);
		u.userData = data;
		return u;
	}
	
	@Deprecated
	public static void resendConfirmation(String accessToken) throws JSONException, IOException, WePayException {
		sendConfirmation(accessToken);
	}

	public static void sendConfirmation(String accessToken) throws JSONException, IOException, WePayException {
		sendConfirmation(null, accessToken);
	}

	public static void sendConfirmation(String emailMessage, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		if (emailMessage != null) {
			params.put("email_message", emailMessage);
		}

		request("/user/send_confirmation", params, accessToken);
	}

	public Long getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userData.firstName + " " + userData.lastName;
	}

	public String getFirstName() {
		return userData.firstName;
	}

	public String getLastName() {
		return userData.lastName;
	}

	public String getEmail() {
		return userData.email;
	}

	public String getState() {
		return state;
	}
	
	public String getScope() {
		return userData.scope;
	}

	public String getCallbackUri() {
		return userData.callbackUri;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public Long[] getRbits() {
		return rbits;
	}

}
