Feature: A user can be created
  Description: The purpose of this feature is to test if a user is created successfully and a positive integer id is returned

  Scenario: A user can be created and an id is returned
    Given A username JohnDoe
    When The user is attempted to be created
    Then A response code of 201 is obtained
    And An ID is returned