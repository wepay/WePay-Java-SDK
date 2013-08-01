package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class App extends WePayResource {
	
	protected Long client_id;
	protected String client_secret;
	protected String status;
	protected AppData appData;

	public static App fetch(String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		String response = request("/app", params, access_token);
		App a = gson.fromJson(response, App.class);
		AppData ad = gson.fromJson(response, AppData.class);
		ThemeObjectData atod = gson.fromJson(response, ThemeObjectData.class);
		ad.theme_object = atod;
		a.appData = ad;
		return a;
	}
	
	public void modify(AppData data, String access_token) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.client_id);
		params.put("client_secret", WePay.client_secret);
		if (data.gaq_domains != null) params.put("gaq_domains", data.gaq_domains);
		if (data.theme_object != null) params.put("theme_object", ThemeObjectData.build_theme_object(data.theme_object));
		JSONObject object = new JSONObject(request("/app/modify", params, access_token));
		this.client_id = WePay.client_id;
		this.status = object.getString("status");
		if (this.appData == null) this.appData = data;
		else {
			if (data.gaq_domains != null) this.appData.gaq_domains = data.gaq_domains; 
			if (data.theme_object != null) this.appData.theme_object = data.theme_object;
		}
	}

	public Long getClient_id() {
		return client_id;
	}

	public String getStatus() {
		return status;
	}
	
	public ThemeObjectData getTheme_object() {
		return appData.theme_object;
	}
	
	public String[] getGaq_domains() {
		return appData.gaq_domains;
	}
	
}
