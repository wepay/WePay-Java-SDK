WePay-Java-SDK
================================

The lib folder contains the jars necessary to use the SDK to make API calls to WePay. The src folder contains all source code used to build the the WePayAPI.jar. 

To use this SDK, first add the WePayAPI.jar to your project. You'll also need to add GSON and JSON jars as well (these are included in the lib folder). To make API calls from within your code, import com.wepay.* (including model, net, exception, and data folders and all of their contents). Before making actual API calls, you'll need to initialize a new WePay object with your WePay app's client ID, client secret, and whether to use stage environment (true) or production environment (false).
```java	
WePay wepay = new WePay();
wepay.initialize(myClientId, myClientSecret, useStageEnv);
```	
Once the WePay object is initialized, you can make all API calls described by the full documentation on WePay's website. For a full listing of available API calls, visit https://www.wepay.com/developer and explore the API Calls section. 

API calls take two arguments, a data argument and an access_token argument. For certain API calls where authorization is needed, you'll need to set the access_token argument appropriately; for calls that don't require authorization, set access_token to null. The data object argument is specific to each class of API call. The following sample code shows how you can make an "/account/create" API call and how you can use the returned object to obtain information about the account. 
```java
AccountData data = new AccountData();
data.name = "Test Person";
data.description = "This is an example account";
data.type = "personal";
/** 
 *   You can include as many parameters as you'd like to the data 
 *   object before executing the API call. However required parameters 
 *   must be included or the call will fail.
 */
Account newAccount = Account.create(data, myAccessToken);
/**
 *   You can now call instance methods on your newAccount object to 
 *   obtain information about the account. 
 */
Long accountId = newAccount.getAccount_id(); 
String verificationState = newAccount.getVerification_state();
```	
All API calls take this sort of format. There are many types of data objects that are necessary for different API calls. These data objects facilitate the passing of valid parameters to API call classes. The data classes can be found in the com/wepay/model/data folder. You can explore all argument types in the code in the src folder and parameter requirements and descriptions can be found in the full WePay API documentation. 

A demo of the WePay API Java SDK can be found at WeFarm, an online demo marketplace built on the WePay API using this SDK. WeFarm is live online at http://wefarm.herokuapp.com/. The Java source code for WeFarm can be found at https://github.com/wepay/WeFarm-Java for closer inspection. 

For further information, visit https://www.wepay.com/developer. 
