package com.wepay.model.data;

import org.json.*;
import java.util.Map;

public class PMCreditCardData {
    public Long id;

    public Map<String, Object> data;

    public static JSONObject buildCreditCard(PMCreditCardData info) throws JSONException {
        JSONObject o = new JSONObject();
        o.put("id", info.id);
        if (info.data != null) {
            o.put("data", info.data);
        }
        return o;
    }
}
