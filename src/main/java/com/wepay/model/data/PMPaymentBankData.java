package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class PMPaymentBankData {
    public Long id;
    public String bankName;
    public String type;

    public JSONObject toJSON() throws JSONException{
        JSONObject o = new JSONObject(); // Responses may contain the bank name and account type, but requests never should
        if (id != null) o.put("id", id);
        return o;
    }

}
