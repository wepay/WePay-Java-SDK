package com.wepay.model;

import java.io.IOException;
import java.util.Map;

import org.json.*;

import com.wepay.WePay;
import com.wepay.net.WePayResource;
import com.wepay.exception.WePayException;
import com.wepay.model.data.*;

public class Batch extends WePayResource {
	
	protected String call;
	protected String referenceId;
	protected Map response;
	
	public static Batch[] create(BatchData[] calls, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject batch = new JSONObject();
		batch.put("client_id", WePay.clientId);
		batch.put("client_secret", WePay.clientSecret);
		JSONArray callsArray = new JSONArray();
		for (int i = 0; i < calls.length; i++) {
			JSONObject call = new JSONObject();
			if (calls[i].parameters != null) call.put("parameters", calls[i].parameters);
			if (calls[i].referenceId != null) call.put("reference_id", calls[i].referenceId);
			if (calls[i].authorization != null) call.put("authorization", calls[i].authorization);
			String callString = "/".concat(calls[i].callClass.toLowerCase());
			if (calls[i].callFunction.endsWith("()")) calls[i].callFunction = calls[i].callFunction.substring(0, calls[i].callFunction.indexOf("("));
			if (!calls[i].callFunction.equalsIgnoreCase("fetch")) callString = callString.concat("/").concat(calls[i].callFunction.toLowerCase());
			call.put("call", callString);
			callsArray.put(call);
		}
		batch.put("calls", callsArray);
		JSONObject object = new JSONObject(request("/batch/create", batch, accessToken));
		JSONArray responses = object.getJSONArray("calls");
		Batch[] response = new Batch[responses.length()];
		for (int i = 0; i < response.length; i++) {
			Batch b = gson.fromJson(responses.get(i).toString(), Batch.class);
			response[i] = b;
		}
		return response;
	}
	
	public String getCall() {
		return call;
	}
	
	public String getReferenceId() {
		return referenceId;
	}
	
	public Map getResponse() {
		return response;
	}
	
}
