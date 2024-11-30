package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ClientSteps {

    private static final Logger logger = LogManager.getLogger(ClientSteps.class);
    private final ClientRequest clientRequest = new ClientRequest();
    private Response response;
    private Client client;
    List<Client> clientList;
    Client lauraClient = null;
    private String currentPhone;


   @Given("there are at least 10 registered clients in the system")
   public void thereAreAtLeast10RegisteredClientsInTheSystem() {
       response = clientRequest.getClients();
       logger.info(response.jsonPath().prettify());
       Assert.assertEquals(200, response.statusCode());
       clientList = clientRequest.getClientsEntity(response);
       if (clientList.size() < 10) {
           logger.warn("There are less than 10 clients in the system.");
           Assert.fail("There are less than 10 clients in the system.");
       } else {
           logger.info("There are at least 10 clients in the system.");
       }
   }

    @When("I find the client with name Laura")
    public void iFindTheClientWithNameLaura() {
        for (Client client : clientList) {
            if ("Laura".equals(client.getName())) {
                lauraClient = client;
                break;
            }
        }
        if (lauraClient == null) {
            logger.warn("No client found with the name Laura.");
            Assert.fail("No client found with the name Laura.");
        } else {
            logger.info("Found client with name Laura: " + lauraClient.toString());
        }
    }

    @Then("save her current phone number")
    public void saveHerCurrentPhoneNumber(){
        String currentPhone = lauraClient.getPhone();
        logger.info("Laura's current phone number is: " + currentPhone);
        this.currentPhone = currentPhone;
    }

    @And("update her phone number")
    public void updateHerPhoneNumber() {
        if (lauraClient == null) {
            logger.warn("Laura's client data is missing, cannot update phone number.");
            Assert.fail("Laura's client data is missing.");
        }
        currentPhone = lauraClient.getPhone();
        String newPhone = "87";
        lauraClient.setPhone(newPhone);
        logger.info("Updated phone number: " + lauraClient.getPhone());
        Response response = clientRequest.updateClient(lauraClient, lauraClient.getId());
        Assert.assertEquals(200, response.statusCode());
        String updatedPhone = response.jsonPath().getString("phone");
        logger.info("Phone number in response: " + updatedPhone);
        Assert.assertEquals(newPhone, updatedPhone);
        logger.info("Laura's phone number updated successfully.");
    }

    @And("validate her phone number is different")
    public void validateHerPhoneNumberIsDifferent() {
        String updatedPhone = lauraClient.getPhone();

        if (currentPhone.equals(updatedPhone)) {
            logger.error("The phone number was not updated. The current phone number is the same.");
            Assert.fail("The phone number was not updated. The current phone number is the same.");
        } else {
            logger.info("The phone number has been successfully updated. Old: " + currentPhone + ", New: " + updatedPhone);
        }
    }
    @And("delete all clients")
    public void deleteAllClients() {
        Response response = clientRequest.getClients();

        Assert.assertEquals(200, response.statusCode());

        List<Client> clients = clientRequest.getClientsEntity(response);

        for (Client client : clients) {
            String clientId = client.getId();
            Response deleteResponse = clientRequest.deleteClient(clientId);

            if (deleteResponse.statusCode() != 200 && deleteResponse.statusCode() != 204) {
                logger.error("Failed to delete client with id: " + clientId);
                Assert.fail("Failed to delete client with id: " + clientId);
            } else {
                logger.info("Successfully deleted client with id: " + clientId);
            }
        }
    }

/*


      @smoke @test1
      Scenario: Get the list of active resources
        Given there are at least five active resources
        When I find all the resources active
        Then save her current phone number
        And Update them as inactive

      @smoke @test1
      Scenario: Update and delete a New Client
        Given i have access to the data
        When I create a new client
        Then i find the new client
        And update any parameter of the new client
        And delete the new client

      @smoke @test1
      Scenario: Update the last created resource
        Given there are at least 15 resources
        When I find the latest resource
        Then i update all the parameters of this resiyrce
            */



    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(DataTable clientData) {
        Map<String, String> clientDataMap = clientData.asMaps().get(0);
        client = Client.builder()
                .name(clientDataMap.get("Name"))
                .lastName(clientDataMap.get("LastName"))
                .gender(clientDataMap.get("Gender"))
                .country(clientDataMap.get("Country"))
                .city(clientDataMap.get("City"))
                .build();
        logger.info("Client mapped: " + client);
    }
    @When("I retrieve the details of the client with ID {string}")
    public void sendGETRequest(String clientId) {
        response = clientRequest.getClient(clientId);
        logger.info(response.jsonPath().prettify());
        logger.info("The status code is: " + response.statusCode());
    }
    
    @When("I send a GET request to view all the clients")
    public void iSendAGETRequestToViewAllTheClient() {
        response = clientRequest.getClients();
    }
    
    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        response = clientRequest.createClient(client);
    }
    
    @When("I send a DELETE request to delete the client with ID {string}")
    public void iSendADELETERequestToDeleteTheClientWithID(String clientId) {
        response = clientRequest.deleteClient(clientId);
    }
    
    @When("I send a PUT request to update the client with ID {string}")
    public void iSendAPUTRequestToUpdateTheClientWithID(String clientId, String requestBody) {
        client = clientRequest.getClientEntity(requestBody);
        response = clientRequest.updateClient(client, clientId);
    }
    
    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }
    
    @Then("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        client = clientRequest.getClientEntity(response);
        Map<String, String> expectedDataMap = expectedData.asMaps().get(0);
        Assert.assertEquals(expectedDataMap.get("Name"), client.getName());
        Assert.assertEquals(expectedDataMap.get("LastName"), client.getLastName());
        Assert.assertEquals(expectedDataMap.get("Gender"), client.getGender());
        Assert.assertEquals(expectedDataMap.get("Country"), client.getCountry());
        Assert.assertEquals(expectedDataMap.get("City"), client.getCity());
        Assert.assertEquals(expectedDataMap.get("Id"), client.getId());
    }
    
    @Then("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        Client new_client = clientRequest.getClientEntity(response);
        new_client.setId(null);
        Assert.assertEquals(client, new_client);
    }
    


}