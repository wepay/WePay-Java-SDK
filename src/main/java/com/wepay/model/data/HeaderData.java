package com.wepay.model.data;

public class HeaderData {
    public String accessToken;
    public String riskToken;
    public String clientIP;

    public int connectionTimeoutSecs = 30;
    public int readTimeoutSecs = 120;
}
