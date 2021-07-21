package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo {
    public String ipAddress;
    public Browser browser;

    public JSONObject toJSON() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("browser", browser.toJSON());
        o.put("ip_address", ipAddress);
        return o;
    }
}
