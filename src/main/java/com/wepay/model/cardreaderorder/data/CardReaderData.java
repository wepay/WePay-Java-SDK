package com.wepay.model.cardreaderorder.data;

import org.json.JSONObject;

/**
 * Created by ryanw on 10/27/15.
 */
public class CardReaderData {
    public String model;

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("model", model);
        return json;
    }
}
