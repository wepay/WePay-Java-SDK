package com.wepay.model.cardreaderorder.data;

import org.json.JSONObject;

/**
 * Created by ryanw on 10/27/15.
 */
public class CardReaderData {
    public String model;

    public String toString() {
        JSONObject json = new JSONObject();

        JSONObject inner = new JSONObject();
        inner.put("name", model);

        json.put("card_reader", inner);

        return json.toString();
    }
}
