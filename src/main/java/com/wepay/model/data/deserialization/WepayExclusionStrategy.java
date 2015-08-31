package com.wepay.model.data.deserialization;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class WepayExclusionStrategy implements ExclusionStrategy{

	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		if (field.getAnnotation(JsonDeserializeIgnore.class) != null) {
			return true;
		}
		return false;
	}

}
