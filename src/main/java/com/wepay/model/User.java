package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class User extends WePayResource {
	
	public Long user_id;
	public String user_name;
	public String state;
	public String access_token;
	public String token_type;
	public Long expires_in;
	public UserData userData;

	public static User fetch(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		String response = request("/user", params, access_token);
		User u = gson.fromJson(response, User.class);
		UserData ud = gson.fromJson(response, UserData.class);
		u.userData = ud;
		return u;
	}
	
	public void modify(String new_callback_uri, String access_token) throws JSONException, IOException, WePayException {
		//overload function for single parameter modification: callback_uri
		UserData data = new UserData();
		data.callback_uri = new_callback_uri;
		this.modify(data, access_token);
	}
	public void modify(UserData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("callback_uri", data.callback_uri);
		String response = request("/user/modify", params, access_token);
		User u = gson.fromJson(response, User.class);
		UserData ud = gson.fromJson(response, UserData.class);
		ud.callback_uri = data.callback_uri;
		this.user_id = u.user_id;
		this.user_name = u.user_name;
		this.state = u.state;
		this.access_token = u.access_token;
		this.token_type = u.token_type;
		this.expires_in = u.expires_in;
		this.userData = ud;
	}
	
	public static User register(UserData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		params.put("email", data.email);
		params.put("scope", data.scope);
		params.put("first_name", data.first_name);
		params.put("last_name", data.last_name);
		params.put("original_ip", data.original_ip);
		params.put("original_device", data.original_device);
		if (data.redirect_uri != null) params.put("redirect_uri", data.redirect_uri);
		User u = gson.fromJson(request("/user/register", params, access_token), User.class);
		u.userData = data;
		return u;
	}
	
	public static void resend_confirmation(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		request("/user/resend_confirmation", params, access_token);
	}

	public Long getUser_id() {
		return user_id;
	}
	
	public String getUser_name() {
		return userData.first_name + " " + userData.last_name;
	}

	public String getFirst_name() {
		return userData.first_name;
	}

	public String getLast_name() {
		return userData.last_name;
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

	public String getCallback_uri() {
		return userData.callback_uri;
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public Long getExpires_in() {
		return expires_in;
	}

}
