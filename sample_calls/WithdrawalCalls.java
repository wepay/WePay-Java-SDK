
public class WithdrawalCalls {
	
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
		WithdrawalData wData = new WithdrawalData();
		wData.accountId = 2111137965;
		Withdrawal myWithdrawal = Withdrawal.create(wData, myAccessToken);
		/**
			myWithdrawal: 
			{
			  "withdrawal_id": 264463950,
			  "withdrawal_uri": "https://stage.wepay.com/api/withdrawal/264463950/9305b4ce3915620d488fb4273025f94d",
			  "withdrawal_data": {
			    "account_id": 2111137965
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FETCH call
		 */
		Withdrawal myWithdrawal = Withdrawal.fetch(70569984, myAccessToken);
		/**
			myWithdrawal: 
			{
			  "withdrawal_id": 264463950,
			  "withdrawal_uri": "https://stage.wepay.com/api/withdrawal/264463950/9305b4ce3915620d488fb4273025f94d",
			  "state": "new",
			  "amount": 132.42,
			  "recipient_confirmed": true,
			  "type": "ach",
			  "create_time": 1375484632,
			  "withdrawal_data": {
			    "account_id": 2111137965,
			    "redirect_uri": "/status/withdrawal_complete/264463950",
			    "note": ""
			  }
			}
		 */
		/****************************************************************************/
		/**
		 * FIND call
		 */
		WithdrawalFindData wfData = new WithdrawalFindData();
		wfData.accountId = 2111137965;
		Withdrawal[] foundList = Withdrawal.find(wfData, myAccessToken);
		/**
		    foundList: 
			[
			  {
			    "withdrawal_id": 264463950,
			    "withdrawal_uri": "https://stage.wepay.com/api/withdrawal/264463950/9305b4ce3915620d488fb4273025f94d",
			    "state": "new",
			    "amount": 132.42,
			    "recipient_confirmed": true,
			    "type": "ach",
			    "create_time": 1375484632,
			    "withdrawal_data": {
			      "account_id": 2111137965,
			      "redirect_uri": "/status/withdrawal_complete/264463950",
			      "note": ""
			    }
			  },
			  {
			    "withdrawal_id": 220321467,
			    "withdrawal_uri": "https://stage.wepay.com/api/withdrawal/220321467/8e5d5f017dd62464bd2ebda107f74d07",
			    "state": "expired",
			    "amount": 78.50,
			    "recipient_confirmed": true,
			    "type": "ach",
			    "create_time": 1373411990,
			    "withdrawal_data": {
			      "account_id": 2111137965,
			      "redirect_uri": "/status/withdrawal_complete/220321467",
			      "note": ""
			    }
			  }
			]
		*/
		/****************************************************************************/
		/**
		 * MODIFY call
		 */
		WithdrawalData newData = new WithdrawalData(); 
		newData.callbackUri = "http://www.yourcallbackuri.com";
		Withdrawal myWithdrawal = new Withdrawal(264463950);
		myWithdrawal.modify(newData, myAccessToken);
		//modifications are made, nothing is returned
		/**
			myWithdrawal:
			{
			  "withdrawal_id": 264463950,
			  "withdrawal_uri": "https://stage.wepay.com/api/withdrawal/264463950/9305b4ce3915620d488fb4273025f94d",
			  "state": "new",
			  "amount": 0,
			  "recipient_confirmed": true,
			  "type": "ach",
			  "create_time": 1375484632,
			  "withdrawal_data": {
			    "account_id": 2111137965,
			    "redirect_uri": "/status/withdrawal_complete/264463950",
			    "callback_uri": "http://www.yourcallbackuri.com",
			    "note": ""
			  }
			}
		 */
		/****************************************************************************/

	}

}
