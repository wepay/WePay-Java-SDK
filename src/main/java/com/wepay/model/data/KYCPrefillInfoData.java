package com.wepay.model.data;

import org.json.JSONException;
import org.json.JSONObject;

public class KYCPrefillInfoData {

  public NameData name;
  public String email;
  public PhoneData phone;
  public PhoneData entityPhone;
  public AddressData address;
  public AddressData entityAddress;
  public DateOfBirthData dateOfBirth;
  public String entityName;
  public String url;
  public String description;

  public static JSONObject buildPrefillInfo(KYCPrefillInfoData info) throws JSONException {
    JSONObject o = new JSONObject();
    if (info.name != null) {
      o.put("name", NameData.buildName(info.name));
    }
    o.put("email", info.email);
    if (info.phone != null) {
      o.put("phone", PhoneData.buildPhone(info.phone));
    }
    if (info.entityPhone != null) {
      o.put("entity_phone", PhoneData.buildPhone(info.entityPhone));
    }
    if (info.address != null) {
      o.put("address", AddressData.buildAddress(info.address));
    }
    if (info.entityAddress != null) {
      o.put("entity_address", AddressData.buildAddress(info.entityAddress));
    }
    if (info.dateOfBirth != null) {
      o.put("date_of_birth", DateOfBirthData.buildDateOfBirth(info.dateOfBirth));
    }
    o.put("entity_name", info.entityName);
    o.put("url", info.url);
    o.put("description", info.description);

    return o;
  }

}
