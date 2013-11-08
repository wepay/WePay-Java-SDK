
public class AccountCalls {
	
	/**
	 * The calls in the main function below are not intended to be executed as they are. 
	 * Think of this code as a template for how you might structure API calls within your 
	 * own code. Below each call is an example of the result of the call (either the call's
	 * response or how the call affect's the objects it is called on. 
	 */
	
	public static void main() {
		
		/****************************************************************************/
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
			  "account_uri": "https://stage.wepay.com/account/2111137965",
			  "account_data": {
			    "name": "John Smith",
			    "description": "This is an example account"
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		Account myAccount = Account.fetch(2111137965, myAccessToken);
		/**
			myAccount: 
			{
			  "account_id": 2111137965,
			  "state": "active",
			  "account_uri": "https://stage.wepay.com/account/2111137965",
			  "payment_limit": 50000,
			  "verification_state": "unverified",
			  "verification_uri": "https://stage.wepay.com/api/account_verify/2111137965/7fbcc1b9",
			  "account_data": {
			    "name": "John Smith",
			    "description": "This is an example account",
			    "type": "personal"
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FIND call
		 */
		AccountFindData afData = new AccountFindData();
		afData.name = "John Smith"
		Account[] foundList = Account.find(afData, myAccessToken);
		/**
			foundList: 
			[
			  {
			    "account_id": 402735274,
			    "state": "active",
			    "account_uri": "https://stage.wepay.com/account/402735274",
			    "payment_limit": 50000,
			    "verification_state": "unverified",
			    "verification_uri": "https://stage.wepay.com/api/account_verify/402735274/7fbcc1b9",
			    "account_data": {
			      "name": "John Smith",
			      "description": "Another John Smith's Account",
			      "type": "personal"
			    }
			  },
			  {
			    "account_id": 2111137965,
			    "state": "active",
			    "account_uri": "https://stage.wepay.com/account/2111137965",
			    "payment_limit": 50000,
			    "verification_state": "unverified",
			    "verification_uri": "https://stage.wepay.com/api/account_verify/2111137965/7fbcc1b9",
			    "account_data": {
			      "name": "John Smith",
			      "description": "This is an example account",
			      "type": "personal"
			    }
			  }
			]
		 */
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		AccountData newData = new AccountData(); 
		newData.callbackUri = "http://www.yourcallbackuri.com";
		Account myAccount = new Account(2111137965);
		myAccount.modify(newData, myAccessToken);
		//modifications are made, nothing is returned
		/**
			myAccount:
			{
			  "account_id": 2111137965,
			  "state": "active",
			  "account_uri": "https://stage.wepay.com/account/2111137965",
			  "payment_limit": 50000,
			  "verification_state": "unverified",
			  "verification_uri": "https://stage.wepay.com/api/account_verify/2111137965/7fbcc1b9",
			  "account_data": {
			    "name": "John Smith",
			    "description": "This is an example account",
			    "type": "personal",
			    "callback_uri": "http://www.yourcallbackuri.com"
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * BALANCE call
		 */
		Account myAccount = new Account(2111137965);
		AccountBalanceData balance = myAccount.balance(myAccessToken);
		/**
			balance:
			{
			  "pending_balance": 171.58,
			  "available_balance": 171.58,
			  "pending_amount": 0,
			  "reserved_amount": 0,
			  "disputed_amount": 0,
			  "currency": "USD"
			}
		 */
		/****************************************************************************/
		/**
		 * ADD_BANK call
		 */
		AccountAddBankData bank = new AccountAddBankData();
		bank.redirectUri = "http://www.yourredirecturi.com";		
		Account myAccount = new Account(2111137965);
		String addBankUri = myAccount.add_bank(bank, myAccessToken);
		/**
			addBankUri: https://stage.wepay.com/api/account_add_bank/2111137965/7fbcc1b9
		 */
		/****************************************************************************/
		/**
		 * SET_TAX and GET_TAX calls
		 */
		AccountTaxData tax1 = new AccountTaxData();
		tax1.percent = 5;
		tax1.country = "US";
		tax1.state = "CA";
		AccountTaxData tax2 = new AccountTaxData();
		tax2.percent = 8;
		tax2.country = "US";
		Account myAccount = new Account(2111137965);
		myAccount.set_tax({tax1, tax2}, myAccessToken);
		//tax is set, nothing is returned
		AccountTaxData[] taxes = myAccount.get_tax(myAccessToken);
		/**
			taxes:
			[
			  {
			    "percent": 5,
			    "country": "US",
			    "state": "CA"
			  },
			  {
			    "percent": 8,
			    "country": "US"
			  }
			]
		 */
		/****************************************************************************/
		/**
		 * DELETE call
		 */
		Account myAccount = new Account(2111137965);
		myAccount.delete(myAccessToken);
		//account is deleted, nothing is returned
		/****************************************************************************/

	}

}
