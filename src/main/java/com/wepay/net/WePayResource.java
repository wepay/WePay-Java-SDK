package com.wepay.net;

import java.io.*;
import java.net.*;

import javax.net.ssl.HttpsURLConnection;

import org.json.*;

import com.google.gson.*;
import com.wepay.WePay;
import com.wepay.exception.WePayException;

public class WePayResource {

	public static String apiEndpoint;
	public static String uiEndpoint;
	protected final static String STAGE_API_ENDPOINT = "https://stage.wepayapi.com/v2";
	protected final static String STAGE_UI_ENDPOINT = "https://stage.wepay.com/v2";
	protected final static String PRODUCTION_API_ENDPOINT = "https://wepayapi.com/v2";
	protected final static String PRODUCTION_UI_ENDPOINT = "https://www.wepay.com/v2";
	
	public static final Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.create();

	public static void initializeWePayResource(Boolean useStageEnv) {
		if (useStageEnv) {
			apiEndpoint = STAGE_API_ENDPOINT;
			uiEndpoint = STAGE_UI_ENDPOINT;
		} else {
			apiEndpoint = PRODUCTION_API_ENDPOINT;
			uiEndpoint = PRODUCTION_UI_ENDPOINT;
		}
	}
	
	protected static javax.net.ssl.HttpsURLConnection httpsConnect(String call, String accessToken) throws IOException {
		URL url = new URL(apiEndpoint + call);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setConnectTimeout(30000); // 30 seconds
		connection.setReadTimeout(100000); // 100 seconds
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Api-Version", "2014-01-08");
		connection.setRequestProperty("User-Agent", "WePay Java SDK");
		if (accessToken != null) {connection.setRequestProperty("Authorization: Bearer", accessToken);}		
		return connection;
	}
	
	public static String request(String call, JSONObject params, String accessToken) throws WePayException, IOException {
		HttpsURLConnection connection = httpsConnect(call, accessToken);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(params.toString());
		wr.flush();
		wr.close();
		boolean error = false;
		int responseCode = connection.getResponseCode();
		InputStream is;
		if (responseCode >= 200 && responseCode < 300) {
			is = connection.getInputStream();
		}
		else {
			is = connection.getErrorStream();
			error = true;
		}
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		String line;
		StringBuffer response = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			response.append(line);
		}
		rd.close();
		String responseString = response.toString();
		if (error) {
			WePayException ex = WePayResource.gson.fromJson(responseString, WePayException.class);
			throw ex;
		}
		return responseString;
	} 

}
