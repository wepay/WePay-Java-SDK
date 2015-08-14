package com.wepay.model.data;

import org.json.*;

public class KYCPrefillInfoData {
    
    public String name;
    public String email;
    public String phone_number;
    public String address;
    public String city;
    public String state;
    public Integer zip;
    public String country;
    public String region;
    public String postcode;
    public String dob;
    public String entityName;
    public String url;
    public String description;

    public static JSONObject buildPrefillInfo(KYCPrefillInfoData info) throws JSONException {
        JSONObject o = new JSONObject();
        o.put("name", info.name);
        o.put("email", info.email);
        o.put("phone_number", info.phone_number);
        o.put("address", info.address);
        o.put("city", info.city);
        o.put("state", info.state);
        o.put("zip", info.zip);
        o.put("country", info.country);
        o.put("region", info.region);
        o.put("postcode", info.postcode);
        o.put("dob", info.dob);
        o.put("entity_name", info.entityName);
        o.put("url", info.url);
        o.put("description", info.description);
        return o;
    }
    
}
