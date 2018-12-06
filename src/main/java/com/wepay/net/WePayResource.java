package com.wepay.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.JsonSyntaxException;
import com.wepay.model.data.HeaderData;
import org.json.JSONObject;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wepay.exception.WePayException;
import com.wepay.model.data.deserialization.WepayExclusionStrategy;


public class WePayResource {

	public static final int defaultConnectionTimeoutSecs = 30;
	public static final int defaultReadTimeoutSecs = 120;

	public static String apiEndpoint;
	public static String uiEndpoint;
	protected final static String STAGE_API_ENDPOINT = "https://stage.wepayapi.com/v2";
	protected final static String STAGE_UI_ENDPOINT = "https://stage.wepay.com/v2";
	protected final static String PRODUCTION_API_ENDPOINT = "https://wepayapi.com/v2";
	protected final static String PRODUCTION_UI_ENDPOINT = "https://www.wepay.com/v2";
	
	public static final Gson gson = new GsonBuilder()
			.addDeserializationExclusionStrategy(new WepayExclusionStrategy())
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

	protected static javax.net.ssl.HttpsURLConnection httpsConnect(String call, HeaderData headerData) throws IOException {
		URL url = new URL(apiEndpoint + call);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setConnectTimeout(defaultConnectionTimeoutSecs * 1000);
		connection.setReadTimeout(defaultReadTimeoutSecs * 1000);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Api-Version", "2018-10-03");
		connection.setRequestProperty("User-Agent", "WePay Java SDK v11.0.0");

		if (headerData != null) {
			if (headerData.accessToken != null) {
				connection.setRequestProperty("Authorization", "Bearer " + headerData.accessToken);
			}
			if (headerData.riskToken != null) {
				connection.setRequestProperty("WePay-Risk-Token", headerData.riskToken);
			}
			if (headerData.clientIP != null) {
				connection.setRequestProperty("Client-IP", headerData.clientIP);
			}
		}

    return connection;
	}
	
	protected static javax.net.ssl.HttpsURLConnection httpsConnect(String call, String accessToken) throws IOException {
		HeaderData headerData = new HeaderData();
		if (accessToken != null) {
			headerData.accessToken = accessToken;
		}
		return httpsConnect(call, headerData);
	}

	public static String request(String call, JSONObject params, HeaderData headerData) throws WePayException, IOException {
		HttpsURLConnection connection = httpsConnect(call, headerData);
		Writer wr=new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
		wr.write(params.toString());
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
      try {
        WePayException ex = WePayResource.gson.fromJson(responseString, WePayException.class);
        throw ex;
      } catch (JsonSyntaxException e) {
        throw new WePayException("Can't parse wepay error response " + responseCode + " body " + responseString);
      }
		}
		return responseString;
	}

	public static String request(String call, JSONObject params, String accessToken) throws WePayException, IOException {
		HeaderData headerData = new HeaderData();
		if (accessToken != null) {
			headerData.accessToken = accessToken;
		}
		return request(call, params, headerData);
	}
}
