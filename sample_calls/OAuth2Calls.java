
public class OAuth2Calls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
		/**
		 * AUTHORIZE call
		 */
		OAuth2Data oData = new OAuth2Data();
		oData.redirectUri = "http://www.mywebsite.com/oauth2/";
		oData.scope = "manage_accounts,collect_payments,view_user,preapprove_payments,send_money";
		String sendUserToThisURL = OAuth2.authorize(oData, null);
		/**
			sendUserToThisURL: "https://stage.wepay.com/v2/oauth2/authorize?client_id=190082&redirectUri=http://www.mywebsite.com/oauth2/&scope=manage_accounts,view_balance,collect_payments,view_user,preapprove_payments,send_money"
		 */
		/****************************************************************************/
		/**
		 * TOKEN call
		 */
		OAuth2Data oData = new OAuth2Data();
		oData.redirectUri = "http://www.mywebsite.com/oauth2/";
		oData.code = "f2afa64e923f7ee1bd6d02bdd12e5e87605dec9f2830f68978";
		String userNewAccessToken = OAuth2.token(oData, null);
		/**
			userNewAccessToken: "STAGE_d76aed9714cc73ae8fb14464dea759db1a7699a5d4717d7c610108bd5f500b55"
		 */
		/****************************************************************************/

	}

}
