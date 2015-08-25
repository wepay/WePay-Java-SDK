package com.wepay.model.cardreaderorder.data;

import org.json.JSONObject;

public class ShippingAddressData {
    public String street1;
    public String street2;
    public String city;
    public String state;
    public int zip;

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street1", street1);
        jsonObject.put("street2", street2);
        jsonObject.put("city", city);
        jsonObject.put("state", state);
        jsonObject.put("zip", zip);
        return jsonObject.toString();
    }
}
