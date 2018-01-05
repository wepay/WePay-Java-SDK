package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class NameData {

  public String first;
  public String middle;
  public String last;

  public static JSONObject buildName(NameData info) throws JSONException {
    JSONObject o = new JSONObject();
    o.put("first", info.first);
    o.put("middle", info.middle);
    o.put("last", info.last);
    return o;
  }

}
