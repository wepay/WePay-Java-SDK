package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class NPOInformationData {
    public String legalName;
    public String ein;

    public JSONObject toJSON() throws JSONException {
        JSONObject o = new JSONObject();
        if (legalName != null) o.put("legal_name", legalName);
        if (ein != null) o.put("ein", ein);
        return o;
    }
}
