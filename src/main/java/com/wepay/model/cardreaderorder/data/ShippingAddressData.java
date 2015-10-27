package com.wepay.model.cardreaderorder.data;

import org.json.JSONObject;

public class ShippingAddressData {
    public String address1;
    public String address2;
    public String city;
    public String state;
    public String zip;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address1", address1);
        jsonObject.put("address2", address2);
        jsonObject.put("city", city);
        jsonObject.put("state", state);
        jsonObject.put("zip", zip);
        return jsonObject.toString();
    }
}
