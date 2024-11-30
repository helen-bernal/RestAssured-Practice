package com.testing.api.stepDefinitions;

import com.testing.api.models.Resource;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResourcesSteps {
    private static final Logger logger = LogManager.getLogger(ResourcesSteps.class);
    private final ResourceRequest resourceRequest = new ResourceRequest();
    private Response response;
    private Resource resource;
    List<Resource> resourceList;
    //Test Case 4
    @Given("there are at least fifteen resources")
    public void thereAreAtLeastFifteenResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());

        resourceList = resourceRequest.getResourcesEntity(response);

        if (resourceList == null) {
            resourceList = new ArrayList<>();
        }

        if (resourceList.size() < 15) {
            logger.warn("There are less than 15 resources in the system. Adding missing resources.");

            while (resourceList.size() < 15) {
                Resource randomResource = new Resource(
                        new Faker().name().firstName(),
                        new Faker().name().lastName(),
                        new Faker().number().numberBetween(1, 100),
                        new Faker().number().randomDouble(2, 10, 100),
                        new Faker().lorem().sentence(),
                        new Faker().random().nextBoolean()
                );

                Response randomResponse = resourceRequest.createResource(randomResource);
                Assert.assertEquals(201, randomResponse.statusCode());
                resourceList.add(randomResource);
                logger.info("Created random resource: " + randomResource.getName());
            }

            logger.info("There are now at least 15 resources in the system.");
        } else {
            logger.info("There are at least 15 resources in the system.");
        }
    }

    @When("I find the latest resource")
    public void iFindTheLatestResource() {
        if (resourceList.isEmpty()) {
            logger.error("No resources found in the list.");
            return;
        }

        resource = resourceList.get(resourceList.size() - 1);
        logger.info("Found the latest resource: " + resource.getName());
    }

    @Then("i update all the parameters of this resource")
    public void iUpdateAllTheParametersOfThisResource() {
        resource.setName(new Faker().name().firstName());
        resource.setTrademark(new Faker().name().lastName());
        resource.setStock(new Faker().number().numberBetween(1, 100));
        resource.setPrice(new Faker().number().randomDouble(2, 10, 100));
        resource.setDescription(new Faker().lorem().sentence());
        resource.setTags(new Faker().lorem().words(3).toString());
        resource.setActive(new Faker().random().nextBoolean());

        Response updateResponse = resourceRequest.updateResource(resource, resource.getId());
        Assert.assertEquals(200, updateResponse.statusCode());
        logger.info("Updated resource: " + resource.getName());
    }
    // Test Case 2
    @Given("there are at least five active resources")
    public void thereAreAtLeastFiveActiveResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.statusCode());

        resourceList = resourceRequest.getResourcesEntity(response);

        List<Resource> activeResources = resourceList.stream()
                .filter(Resource::isActive)
                .collect(Collectors.toList());

        if (activeResources.size() < 5) {
            logger.warn("There are less than 5 active resources in the system. Adding missing active resources.");

            while (activeResources.size() < 5) {
                Resource randomResource = new Resource(
                        new Faker().name().firstName(),
                        new Faker().name().lastName(),
                        new Faker().number().numberBetween(1, 100),
                        new Faker().number().randomDouble(2, 10, 100),
                        new Faker().lorem().sentence(),
                        new Faker().random().nextBoolean()
                );

                randomResource.setActive(true);

                Response randomResponse = resourceRequest.createResource(randomResource);
                Assert.assertEquals(201, randomResponse.statusCode());
                activeResources.add(randomResource);
                logger.info("Created active resource: " + randomResource.getName());
            }

            logger.info("There are now at least 5 active resources in the system.");
        } else {
            logger.info("There are at least 5 active resources in the system.");
        }
    }

    @When("I find all the resources active")
    public void iFindAllTheResourcesActive() {
        resourceList = resourceRequest.getResourcesEntity(response);
        resourceList = resourceList.stream()
                .filter(Resource::isActive)
                .collect(Collectors.toList());

        logger.info("Found " + resourceList.size() + " active resources.");
    }

    @Then("Update them as inactive")
    public void updateThemAsInactive() {
        for (Resource resource : resourceList) {
            resource.setActive(false);
            Response updateResponse = resourceRequest.updateResource(resource, resource.getId());
            Assert.assertEquals(200, updateResponse.statusCode());
            logger.info("Updated resource " + resource.getName() + " to inactive.");
        }
    }

    @And("delete all resources")
    public void deleteAllResources() {
        response = resourceRequest.getResources();
        Assert.assertEquals(200, response.statusCode());
        resourceList = resourceRequest.getResourcesEntity(response);

        for (Resource resource : resourceList) {
            String resourceId = resource.getId();
            Response deleteResponse = resourceRequest.deleteResource(resourceId);

            if (deleteResponse.statusCode() != 200 && deleteResponse.statusCode() != 204) {
                logger.error("Failed to delete resource with id: " + resourceId);
                Assert.fail("Failed to delete resource with id: " + resourceId);
            } else {
                logger.info("Successfully deleted resource with id: " + resourceId);
            }
        }
    }


}