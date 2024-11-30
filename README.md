RestAssured Practice
For this practice, we will implement 4 test cases for an API in a development environment that
complies with the following tools and technologies:
● Java
● Maven
● RestAssured
● POJO
● Cucumber
The test cases should be flexible and maintainable to adapt to future changes in endpointsor
test data, and they should run automatically and independently without errors. The sourcecodeshould follow coding best practices, including Git Repository, README, and JavaDocdocumentation where appropriate. For this practice, we will use mockApi.io, and the base URL will be as follows:
https://673bdac096b8dcd5f3f7afdb.mockapi.io/api/v1/clients
Test Case 1: Change the phone number of the first Client named Laura
Pre-Conditions:
- Have at least 10 registered clients
  Steps:
- Find the first client named Laura
- Save her current phone number
- Update her phone number
- Validate her new phone number is different
  Post-Conditions:
- Delete all the registered clients
  Other possible verifications:
  ● Verify that the response is equal to an HTTP status code of 200. ● Verify the structure of the response  body scheema
  Test Case 2: Get the list of active resources
  Pre-Conditions:
- Have at least 5 active resources
  Steps:
- Find all resources active
  Post-Conditions:
- Update them as inactive
  Other possible verifications:
  ● Verify that the response is equal to an HTTP status code of 200. ● Verify the structure of the response body schema. Test Case 3: Update and delete a New Client
  Pre-Conditions:
- N/A
  Steps:
- Create a new client - Find the new client - Update any parameter of the new client - Delete the new client
  Post-Conditions:
- N/A
  Other possible verifications:
  ● Verify that the response is equal to an HTTP status code of 200. ● Verify the structure of the response body schema. ● Verify the response body data after the update. Test Case 4: Update the last created resource
  Pre-Conditions:
- Have at least 15 resources
  Steps:
- Find the latest resource
- Update all the parameters of this resource
  Post-Conditions:
- N/A
  Other possible verifications:
  ● Verify that the response is equal to an HTTP status code of 200. ● Verify the structure of the response body schema. ● Verify the response body data after the update