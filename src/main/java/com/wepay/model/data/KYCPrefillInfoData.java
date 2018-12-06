package com.wepay.model.data;

import org.json.*;

public class KYCPrefillInfoData {
    
    public NameData name;
    public String email;
    public InternationalPhoneNumberData phone;
    public AddressData address;
    public DateOfBirthData dob;
    public String entityName;
    public InternationalPhoneNumberData entityPhone;
    public AddressData entityAddress;
    public String url;
    public String description;

    public static JSONObject buildPrefillInfo(KYCPrefillInfoData info) throws JSONException {
        JSONObject o = new JSONObject();
        if (info.name != null) {
            o.put("name", NameData.buildName(info.name));
        }
        o.put("email", info.email);
        if (info.phone != null) {
            o.put("phone_number", InternationalPhoneNumberData.buildInternationalPhoneNumber(info.phone));
        }
        if (info.address != null) {
            o.put("address", AddressData.buildUnifiedAddress(info.address));
        }
        if (info.dob != null) {
            o.put("dob", DateOfBirthData.buildDateOfBirth(info.dob));
        }
        o.put("entity_name", info.entityName);
        if (info.entityPhone != null) {
            o.put("entity_phone", InternationalPhoneNumberData.buildInternationalPhoneNumber(info.entityPhone));
        }
        if (info.entityAddress != null) {
            o.put("entity_address", AddressData.buildUnifiedAddress(info.entityAddress));
        }
        o.put("url", info.url);
        o.put("description", info.description);
        return o;
    }
    
}
