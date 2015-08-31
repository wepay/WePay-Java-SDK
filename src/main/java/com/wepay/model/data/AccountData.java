package com.wepay.model.data;

import com.wepay.model.data.RbitData;
import com.wepay.model.data.CountryOptionsData;
import com.wepay.model.data.deserialization.JsonDeserializeIgnore;

public class AccountData {
	public String name;
	public String description;
	public String referenceId;
	public String type; 
	public String imageUri;
	public String[] gaqDomains;
	public ThemeObjectData themeObject;
	public Integer mcc;
	public String callbackUri;
    public String country;
    public String[] currencies;
    public CountryOptionsData countryOptions;
    public Integer feeScheduleSlot;
    
    @JsonDeserializeIgnore
    public RbitData[] rbits;
}
