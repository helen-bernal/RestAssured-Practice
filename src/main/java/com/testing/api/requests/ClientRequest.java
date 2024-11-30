package com.testing.api.requests;

import com.google.gson.Gson;
import com.testing.api.models.Client;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import com.github.javafaker.Faker;


import java.util.List;

import static io.restassured.RestAssured.given;

public class ClientRequest extends BaseRequest {

    private String endpoint;
    
    /**
     Get Client list
     
     @return rest-assured response
     */
    public Response getClients() {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }
    
    /**
     Get client by id
     
     @param clientId string
     @return rest-assured response
     */
    public Response getClient(String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestGet(endpoint, createBaseHeaders());
    }
    
    /**
     Update client by id
     
     @param client model
     @param clientId string
     @return rest-assured response
     */
    public Response updateClient(Client client, String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestPut(endpoint, createBaseHeaders(), client);
    }
    
    /**
     Delete client by id
     
     @param clientId string
     @return rest-assured response
     */
    public Response deleteClient(String clientId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.CLIENTS_PATH, clientId);
        return requestDelete(endpoint, createBaseHeaders());
    }
    
    public Client getClientEntity(@NotNull Response response) {
        return response.as(Client.class);
    }
    
    public List<Client> getClientsEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Client.class);
    }
    
    public Response createDefaultClient() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createClient(jsonFile.getClientByJson(Constants.DEFAULT_CLIENT_FILE_PATH));
    }
    
    /**
     Create client
     
     @param client model
     @return rest-assured response
     */
    public Response createClient(Client client) {
        endpoint = String.format(Constants.URL, Constants.CLIENTS_PATH);
        return requestPost(endpoint, createBaseHeaders(), client);
    }
    
    public Client getClientEntity(String clientJson) {
        Gson gson = new Gson();
        return gson.fromJson(clientJson, Client.class);
    }
    
    public boolean validateSchema(Response response, String schemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true;
        } catch (AssertionError e) {
            return false;
        }

    }

    public class ClientDataGenerator {

        public static void main(String[] args) {
            Faker faker = new Faker();

            String name = faker.name().firstName();
            String lastName = faker.name().lastName();
            String country = faker.address().country();
            String city = faker.address().city();
            String email = faker.internet().emailAddress();
            String phone = faker.phoneNumber().phoneNumber();

            System.out.println("Name: " + name);
            System.out.println("Last Name: " + lastName);
            System.out.println("Country: " + country);
            System.out.println("City: " + city);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
        }
    }

}
