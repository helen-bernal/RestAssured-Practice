# RestAssured API Practice Project

This project demonstrates API automation testing using RestAssured, Maven, Java, POJO, and Cucumber. The tests are designed to be flexible, maintainable, and independent, adhering to best coding practices. The base API for testing is hosted on mockApi.io.

## Tools & Technologies Used:
- **Java**
- **Maven**
- **RestAssured**
- **POJO (Plain Old Java Object)**
- **Cucumber**

## API Base URL:
https://673bdac096b8dcd5f3f7afdb.mockapi.io/api/v1/


## Test Cases

### 1. **Change the phone number of the first client named Laura**
#### Pre-Conditions:
- At least 10 registered clients.

#### Steps:
1. Find the first client named "Laura".
2. Save her current phone number.
3. Update her phone number.
4. Validate that her new phone number is different from the old one.

#### Post-Conditions:
- Delete all the registered clients.

#### Verifications:
- HTTP status code 200.
- Validate the structure of the response body schema.


### 2. **Get the list of active resources**
#### Pre-Conditions:
- At least 5 active resources.

#### Steps:
1. Retrieve all active resources.

#### Post-Conditions:
- Update all resources to inactive.

#### Verifications:
- HTTP status code 200.
- Validate the structure of the response body schema.


### 3. **Update and delete a new client**
#### Pre-Conditions:
- N/A

#### Steps:
1. Create a new client.
2. Find the newly created client.
3. Update any parameter of the client.
4. Delete the new client.

#### Post-Conditions:
- N/A

#### Verifications:
- HTTP status code 200.
- Validate the structure of the response body schema.
- Validate the response body data after the update.


### 4. **Update the last created resource**
#### Pre-Conditions:
- At least 15 resources.

#### Steps:
1. Find the latest created resource.
2. Update all parameters of the resource.

#### Post-Conditions:
- N/A

#### Verifications:
- HTTP status code 200.
- Validate the structure of the response body schema.
- Validate the response body data after the update.


## Setup Instructions

### Prerequisites:
- Java 11 or higher
- Maven
- IDE like IntelliJ IDEA 
