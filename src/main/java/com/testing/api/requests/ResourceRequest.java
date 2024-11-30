package com.testing.api.requests;

import com.google.gson.Gson;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;
import com.github.javafaker.Faker;
import com.testing.api.models.Resource;


import java.text.DecimalFormat;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ResourceRequest extends BaseRequest {

    private String endpoint;

    /**
     Get Resourse list

     @return rest-assured response
     */
    public Response getResources() {
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return requestGet(endpoint, createBaseHeaders());
    }

    /**
     Get resource by id

     @param resourceId string
     @return rest-assured response
     */
    public Response getResource(String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCES_PATH, resourceId);
        return requestGet(endpoint, createBaseHeaders());
    }

    /**
     Update resource by id

     @param resource model
     @param resourceId string
     @return rest-assured response
     */
    public Response updateResource(Resource resource, String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCES_PATH, resourceId);
        return requestPut(endpoint, createBaseHeaders(), resource);
    }

    /**
     Delete client by id

     @param resourceId string
     @return rest-assured response
     */
    public Response deleteResource(String resourceId) {
        endpoint = String.format(Constants.URL_WITH_PARAM, Constants.RESOURCES_PATH, resourceId);
        return requestDelete(endpoint, createBaseHeaders());
    }

    public Resource getResourceEntity(@NotNull Response response) {
        return response.as(Resource.class);
    }

    public List<Resource> getResourcesEntity(@NotNull Response response) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("", Resource.class);
    }

    public Response createDefaultResource() {
        JsonFileReader jsonFile = new JsonFileReader();
        return this.createResource(jsonFile.getResourceByJson(Constants.DEFAULT_RESOURCE_FILE_PATH));
    }

    /**
     Create resource

     @param resource model
     @return rest-assured response
     */
    public Response createResource(Resource resource) {
        endpoint = String.format(Constants.URL, Constants.RESOURCES_PATH);
        return requestPost(endpoint, createBaseHeaders(), resource);
    }

    public Resource getResourceEntity(String resourcejson) {
        Gson gson = new Gson();
        return gson.fromJson(resourcejson, Resource.class);
    }

    public boolean validateSchema(Response response, String schemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true;
        } catch (AssertionError e) {
            return false;
        }

    }

    public class ResourceDataGenerator {

        public static void main(String[] args) {
            Faker faker = new Faker();
            String name = faker.name().fullName();
            String trademark = faker.company().name();
            Integer stock = faker.number().numberBetween(1, 100);
            Double price = faker.number().randomDouble(2, 10, 100);
            String description = faker.lorem().sentence();
            Boolean active = faker.random().nextBoolean();

            System.out.println("Name: " + name);
            System.out.println("Trademark: " + trademark);
            System.out.println("Stock: " + stock);
            System.out.println("Price: " + price);
            System.out.println("Description: " + description);
            System.out.println("Active: " + active);
        }
    }




}
