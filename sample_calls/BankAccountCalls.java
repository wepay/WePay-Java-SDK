public class BankAccountCalls {

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
        BankAccountData baData = new BankAccountData();
        baData.clientId = "523";
        baData.email = "sample@example.com";
        baData.accountID = "1234576329";
        baData.usInstitution = new USInstitutionData();

        BankAccount ba = BankAccount.create(baData, myAccessToken);

    }

}