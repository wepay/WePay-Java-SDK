package com.wepay.model;

import java.io.IOException;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class App extends WePayResource {
	
	protected Long clientId;
	protected String clientSecret;
	protected String status;
	protected AppData appData;

	public static App fetch(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		String response = request("/app", params, accessToken);
		App a = gson.fromJson(response, App.class);
		AppData ad = gson.fromJson(response, AppData.class);
		ThemeObjectData atod = gson.fromJson(response, ThemeObjectData.class);
		ad.themeObject = atod;
		a.appData = ad;
		return a;
	}
	
	public void modify(AppData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("client_id", WePay.clientId);
		params.put("client_secret", WePay.clientSecret);
		if (data.gaqDomains != null) params.put("gaq_domains", data.gaqDomains);
		if (data.themeObject != null) params.put("theme_object", ThemeObjectData.buildThemeObject(data.themeObject));
		JSONObject object = new JSONObject(request("/app/modify", params, accessToken));
		this.clientId = WePay.clientId;
		this.status = object.getString("status");
		if (this.appData == null) this.appData = data;
		else {
			if (data.gaqDomains != null) this.appData.gaqDomains = data.gaqDomains; 
			if (data.themeObject != null) this.appData.themeObject = data.themeObject;
		}
	}

	public Long getClientId() {
		return clientId;
	}

	public String getStatus() {
		return status;
	}
	
	public ThemeObjectData getThemeObject() {
		return appData.themeObject;
	}
	
	public String[] getGaqDomains() {
		return appData.gaqDomains;
	}
	
}
