package com.wepay.model.data;
import org.json.*;
import java.math.BigDecimal;

public class FeeData {
	public BigDecimal appFee;
	public String feePayer;
    public BigDecimal processingFee;

	public static JSONObject build_fee(FeeData info) throws JSONException {
		JSONObject o = new JSONObject();
		if (info.appFee != null) o.put("app_fee", info.appFee);
		if (info.feePayer != null) o.put("fee_payer", info.feePayer);
		return o;
	}
}
