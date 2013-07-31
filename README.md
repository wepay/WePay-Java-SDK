WePay-Java-SDK
================================

The lib folder contains the jars necessary to use the SDK to make API calls to WePay. The src folder contains all source code used to build the the WePayAPI.jar. 

To use this SDK, first add the WePayAPI.jar to your project. You'll need to add GSON and JSON jars as well (these are included in the lib folder). You'll also need to import the necessary classes and packages into your own code. 
```java
import com.wepay.WePay;                     // WePay object needed for API initialization
import com.wepay.model.*;                   // contains call classes and all API call functions
import com.wepay.model.data.*;              // contains all data objects needed for making calls
import com.wepay.net.WePayResource;         // network resource used to execute calls
import com.wepay.exception.WePayException;  // handles WePay exceptions
import org.json.*;                          // SDK uses JSON when handling API call parameters
import com.google.gson.*;                   // SDK uses GSON for building objects from API responses
```
Before making actual API calls, you'll need to initialize a new WePay object with your WePay application's client ID, client secret, and whether you want to use the stage environment (true) or the production environment (false).
```java	
WePay wepay = new WePay();
wepay.initialize(myClientId, myClientSecret, useStageEnv);
```	
Once the WePay object is initialized, you can make all of the API calls described by the documentation on WePay's website. For a full listing of available API calls, visit https://www.wepay.com/developer and explore the API Calls section. 

API calls through the Java SDK generally take two arguments, a data object argument and an access_token argument. For API calls where authorization is needed, you'll need to include the access_token argument appropriately; for calls that don't require authorization, you can set access_token to null. 

Data objects facilitate the passing of valid parameters to API call classes. Most data objects are designed specifically as parameters to certain types of API calls. Other data objects are utilized by multiple API calls. Explore the source code in the model folder and data sub-folder to see which types of calls take which data object parameters.

You *must* include all required parameters to successfully execute API calls. You can also include as many optional parameters as you'd like to the data object before executing the API call. For a full listing of required parameters for each call, visit the API documentation. 

The following examples show how to make a few simple API calls and how you can use the returned objects to obtain information. 
```java
// create a new account
AccountData aData = new AccountData();
aData.name = "Test Person";                        // required parameter
aData.description = "This is an example account";  // required parameter
aData.type = "personal";                           // optional parameter
Account newAccount = Account.create(aData, myAccessToken);

// set tax for an account
AccountTaxData taxData = new AccountTaxData();
taxData.percent = 7;
taxData.country = "US";
newAccount.set_tax(taxData, "newAccountAccessToken");

// create a new checkout
CheckoutData cData = new CheckoutData();
cData.account_id = newAccount.getAccount_id();     // use a returned object to access information
cData.short_description = "Soccer Ball Purchase";
cData.type = "GOODS";
cData.amount = 29.95;
cData.app_fee = 0.50;
cData.fee_payer = "payee";
Checkout newCheckout = Checkout.create(cData, "newAccountAccessToken");
```
A demo of the WePay API Java SDK can be found at WeFarm, an online demo marketplace built on the WePay API using this SDK. WeFarm is live online at http://wefarm.herokuapp.com/. You can also find the Java source code for WeFarm at https://github.com/wepay/WeFarm-Java to see how WeFarm uses this SDK to execute WePay API calls. 

For further information, visit https://www.wepay.com/developer. 
