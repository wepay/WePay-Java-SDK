package com.wepay.model.data;
import java.math.BigDecimal;
import org.json.*;

public class HostedCheckoutData {
    public String mode;
	public String redirectUri;
	public String fallbackUri;
	public BigDecimal shippingFee;
	public Boolean requireShipping;
	public PrefillInfoData prefillInfo;
	public String[] fundingSources;
	public ThemeObjectData themeObject;
    public String checkoutUri;
    public AddressData shippingAddress;
    
	public static JSONObject build_hosted_checkout(HostedCheckoutData info) throws JSONException {
		JSONObject o = new JSONObject();
		if (info.mode != null) o.put("mode", info.mode);
		if (info.redirectUri != null) o.put("redirect_uri", info.redirectUri);
		if (info.fallbackUri != null) o.put("fallback_uri", info.fallbackUri);
		if (info.shippingFee != null) o.put("shipping_fee", info.shippingFee);
		if (info.requireShipping != null) o.put("require_shipping", info.requireShipping);
		if (info.prefillInfo != null) o.put("prefill_info", PrefillInfoData.buildPrefillInfo(info.prefillInfo));
		if (info.fundingSources != null) o.put("funding_sources", info.fundingSources);
		if (info.themeObject != null) o.put("theme_object", ThemeObjectData.buildThemeObject(info.themeObject));
		return o;
	}
}
