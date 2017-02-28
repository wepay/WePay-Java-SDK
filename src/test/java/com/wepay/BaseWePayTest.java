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
		//wepay = new WePay();
		Long appClientId = (long)27801;
		String appClientSecret = System.getenv("appClientSecret");
		System.out.println("Secret:" + appClientSecret);
		String foo = System.getenv("Foo");
		System.out.print("Foo: " + foo);
		WePay.initialize(appClientId, appClientSecret, false);
		Assert.assertEquals(appClientId, WePay.clientId);
	}
	

}
