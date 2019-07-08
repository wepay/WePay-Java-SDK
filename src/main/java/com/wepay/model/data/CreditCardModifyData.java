package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class CreditCardModifyData {
    public Long creditCardId;
    public Boolean autoUpdate;
    public String callbackUri;
    public Boolean cardOnFile;
    public Boolean recurring;
}
