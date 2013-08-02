
public class UserCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		User myUser = User.fetch(myAccessToken);
		/**
			myUser: 
			{
			  "user_id": 128820703,
			  "user_name": "WePay User",
			  "state": "registered",
			  "user_data": {
			    "email": "wepayuser@gmail.com",
			    "first_name": "WePay",
			    "last_name": "User"
			  }
			}	
		 */
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		User myUser = new User();
		myUser.modify("http://www.yourcallbackuri.com", myAccessToken);
		//modifications are made, nothing is returned
		/**
			myUser:
			{
			  "user_id": 128820703,
			  "user_name": "WePay User",
			  "state": "registered",
			  "user_data": {
			    "email": "wepayuser@gmail.com",
			    "first_name": "WePay",
			    "last_name": "User"
			    "callback_uri": "http://www.yourcallbackuri.com"
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * REGISTER and RESEND_CONFIRMATION calls
		 */
		
		/**
		 * We suggest you do not use the REGISTER or RESEND_CONFIRMATION calls. 
		 * Instead, go through the OAuth2 flow. See OAuth2Calls in the sample
		 * calls to see how to use this authorization flow.
		 */
		/****************************************************************************/

	}

}
