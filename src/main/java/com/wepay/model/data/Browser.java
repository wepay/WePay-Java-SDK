package com.wepay.model.data;

import org.json.JSONObject;

public class Browser {
    public String type;
    public String challengeIframeSize;
    public String acceptedHeaders;
    public Integer colorDepth;
    public Boolean javaEnabled;
    public String language;
    public Integer screenHeight;
    public Integer screenWidth;
    public Integer timeZone;

    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        o.put("type", type);
        o.put("challenge_iframe_size", challengeIframeSize);
        o.put("accept_headers", acceptedHeaders);
        o.put("color_depth", colorDepth);
        o.put("java_enabled", javaEnabled);
        o.put("language", language);
        o.put("screen_height", screenHeight);
        o.put("screen_width", screenWidth);
        o.put("time_zone", timeZone);
        return o;
    }
}
