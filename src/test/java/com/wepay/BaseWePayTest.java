package com.wepay;

import com.wepay.WePay;                     // WePay object needed for API initialization
import com.wepay.model.*;                   // contains call classes and all API call functions
import com.wepay.model.data.*;              // contains all data objects needed for making calls
import com.wepay.net.WePayResource;         // network resource used to execute calls
import com.wepay.exception.WePayException;  // handles WePay exceptions
import org.json.*;                          // SDK uses JSON when handling API call parameters
import com.google.gson.*;                   // SDK uses GSON for building objects from API responses
import org.junit.Assert;
import org.junit.Test;

public class BaseWePayTest {
	public static WePay wepay;
	
	@Test
	public void testInitialize() {
		String appClient = System.getenv("appClientId");
		Long appClientId = (appClient == null) ? (long)171482 : Long.parseLong(appClient);
		String appClientSecret = System.getenv("appClientSecret");
		WePay.initialize(appClientId, appClientSecret, true);
		Assert.assertEquals(appClientId, WePay.clientId);
	}
	

}
