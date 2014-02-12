
public class AccountCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/**
		 * CREATE call
		 */
		AccountData aData = new AccountData();
		aData.name = "John Smith";
		aData.description = "This is an example account";
		Account myAccount = Account.create(aData, myAccessToken);
		
		/**
			myAccount:
			{
			  "account_id": 2111137965,
			  "state": "active",
			  "create_time": 7111137965,
			  "balances": {}
			  "name":"Example Account",
			  "description":"This is just an example WePay account.",
			  "reference_id":"abc123",
			  "image_uri":"https://stage.wepay.com/img/logo.png",
			  "country": "US",
			  "currencies": ["USD"]
			}
			
			
		*/
		
		/**
		 * FETCH call
		 */
		Account myAccount = Account.fetch(2111137965, myAccessToken);
		
		
		/**
		 * FIND call
		 */
		AccountFindData afData = new AccountFindData();
		afData.name = "John Smith"
		Account[] foundList = Account.find(afData, myAccessToken);
		
		
		/**
		 * MODIFY call
		 */
		AccountData newData = new AccountData(); 
		newData.callbackUri = "http://www.yourcallbackuri.com";
		Account myAccount = new Account(2111137965);
		myAccount.modify(newData, myAccessToken);
		
		/**
		 * GET UPDATE URI call
		 */
		AccountUpdateUriData uData = new AccountUpdateUriData(); 
		uData.mode = "iframe";
		uData.redirectUri = "http://www.cnn.com";
		System.out.println(myAccount.getUpdateUri(uData, myAccessToken));

		
		/**
		 * GET RESERVE DETAILS call
		 */
		AccountReserveData ar = myAccount.getReserveDetails("USD", myAccessToken);
		
		
		/**
		 * DELETE call
		 */
		Account myAccount = new Account(2111137965);
		myAccount.delete(myAccessToken);
		//account is deleted, nothing is returned
		
		
		
	}

}
