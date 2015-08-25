package com.wepay.model.cardreaderorder.data;

import org.json.JSONObject;

public class ShippingContactData {
    public String name;
    public String company;
    public String phone;
    public String email;

    public String toString() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("company", company);
        json.put("email", email);
        json.put("phone", phone);
        return json.toString();
    }
}
