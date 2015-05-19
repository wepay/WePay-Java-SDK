package com.wepay.model.data;

import java.util.Map;

import com.wepay.model.Rbit;

import org.json.*;

public class RbitData {

	public Long rbitId;
	public String associatedObjectType;
	public Long associatedObjectId;
	public String type;
	public Map<String, Object> properties;
	public Long receiveTime;
	public String source;
	public String note;
	public Rbit[] relatedRbits;

}
