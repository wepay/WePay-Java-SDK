package com.wepay.model.data;

import org.json.JSONObject;

/**
 * Created by jacobk on 4/8/16.
 */
public class PMBankAccountData {
    public Long bankAccountID;
    public String bankName;
    public String type;

    public JSONObject toJSON() {
        JSONObject o = new JSONObject(); // Responses may contain the bank name and account type, but requests never should
        if (bankAccountID != null) o.put("id", bankAccountID);
        return o;
    }

}
