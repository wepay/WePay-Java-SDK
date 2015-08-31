package com.wepay.model.data;

import com.wepay.model.data.RbitData;
import com.wepay.model.data.deserialization.JsonDeserializeIgnore;


public class UserData {
	
	public String email; 		
	public String scope; 			
	public String firstName; 		
	public String lastName; 		
	public String originalIp; 				
	public String originalDevice;
	public Long tosAcceptanceTime;
	public String redirectUri; 
	public String callbackUri;
	
	@JsonDeserializeIgnore
    public RbitData[] rbits;
}
