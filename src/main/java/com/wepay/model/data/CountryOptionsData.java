package com.wepay.model.data;

import org.json.*;

public class CountryOptionsData {

    public Boolean debitOptIn;
    
    public static JSONObject buildCountryOptions(CountryOptionsData info) throws JSONException {
        JSONObject o = new JSONObject();
        o.put("debit_opt_in", info.debitOptIn);
        return o;
    }
}
