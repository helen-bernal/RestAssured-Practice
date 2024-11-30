#@active
Feature: Client testing CRUD
#My cases

  @active
  @smoke @test1
  Scenario: Change the phone number of the first Client named Laura
    Given there are at least 10 registered clients in the system
    When I find the client with name Laura
    Then save her current phone number
    And update her phone number
    And validate her phone number is different
    And delete all clients


  @smoke
  Scenario: Get the list of active resources
    Given there are at least five active resources
    When I find all the resources active
    Then save her current phone number
    And Update them as inactive

  @smoke
  Scenario: Update and delete a New Client
    Given i have access to the data
    When I create a new client
    Then i find the new client
    And update any parameter of the new client
    And delete the new client

  @smoke
  Scenario: Update the last created resource
    Given there are at least 15 resources
    When I find the latest resource
    Then i update all the parameters of this resiyrce