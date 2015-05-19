package com.wepay.model;

import com.wepay.exception.WePayException;
import com.wepay.net.WePayResource;
import com.wepay.model.data.*;
import com.wepay.WePay;

import java.io.IOException;
import java.util.Map;

import org.json.*;

public class Rbit extends WePayResource {
		
	protected Long rbitId;
	protected String associatedObjectType;
	protected Long associatedObjectId;
	protected String type;
	protected Map<String, Object> properties;
	protected Long receiveTime;
	protected String source;
	protected String note;
	protected Rbit[] relatedRbits;
	protected RbitData rbitData;
	
	public Rbit(Long rbitId) {
		this.rbitId = rbitId;
	}

	public static Rbit fetch(Long rbitId, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("rbit_id", rbitId);
		String response = request("/rbit", params, accessToken);
		Rbit r = gson.fromJson(response, Rbit.class);
		RbitData rd = gson.fromJson(response, RbitData.class);
		r.rbitData = rd;
		return r;
	}

	public static Rbit[] find(RbitFindData findData, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		
		if (findData.associatedObjectType != null) {
			params.put("associated_object_type", findData.associatedObjectType);
		}

		if (findData.associatedObjectId != null) {
			params.put("associated_object_id", findData.associatedObjectId);
		}

		if (findData.type != null) {
			params.put("type", findData.type);
		}

		if (findData.source != null) {
			params.put("source", findData.source);
		}

		JSONArray results = new JSONArray(request("/rbit/find", params, accessToken));
		Rbit[] found = new Rbit[results.length()];
		for (int i = 0; i < found.length; i++) {
			Rbit r = gson.fromJson(results.get(i).toString(), Rbit.class);
			RbitData rd = gson.fromJson(results.get(i).toString(), RbitData.class);
			r.rbitData = rd;
			found[i] = r;
		}
		return found;
	}

	public static Rbit create(RbitData data, String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("associated_object_type", data.associatedObjectType);
		params.put("associated_object_id", data.associatedObjectId);
		params.put("type", data.type);
		params.put("properties", data.properties);
		params.put("receive_time", data.receiveTime);
		params.put("source", data.source);
		
		if (data.note != null) {
			params.put("note", data.note);
		}

		if (data.relatedRbits != null) {
			params.put("related_rbits", data.relatedRbits);
		}
		
		String response = request("/rbit/create", params, accessToken);
		Rbit r = gson.fromJson(response, Rbit.class);
		r.rbitData = data;
		return r;
	}

	public void delete(String accessToken) throws JSONException, IOException, WePayException {
		JSONObject params = new JSONObject();
		params.put("rbit_id", this.rbitId);
		request("/rbit/delete", params, accessToken);
	}

	public Long getRbitId() {
		return rbitId;
	}
	
	public String getAssociatedObjectType() {
		return associatedObjectType;
	}
	
	public Long getAssociatedObjectId() {
		return associatedObjectId;
	}
    
	public String getType() {
		return type;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public Long getReceiveTime() {
		return receiveTime;
	}

	public String getSource() {
		return source;
	}

	public String getNote() {
		return note;
	}

	public Rbit[] getRelatedRbits() {
		return relatedRbits;
	}

}
