package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class PMBankAccountData {
    public Long bankAccountId;
    public String bankName;
    public String type;

    public JSONObject toJSON() throws JSONException{
        JSONObject o = new JSONObject(); // Responses may contain the bank name and account type, but requests never should
        if (bankAccountId != null) o.put("id", bankAccountId);
        return o;
    }

}
