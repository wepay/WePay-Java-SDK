package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class StrongCustomerAuthentication {
    public String redirectUri;
    public String challengeMode;
    public DeviceInfo deviceInfo;

    public JSONObject toJSON() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("redirect_uri", redirectUri);
        o.put("challenge_mode", challengeMode);
        if (deviceInfo != null) {
            o.put("device_information", deviceInfo.toJSON());
        }

        return o;
    }
}
