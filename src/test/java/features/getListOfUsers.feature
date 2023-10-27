Feature: Rest Api tests for Reqres.in

  Scenario: We get a list of users and apply negative and positive cases
    Given We are getting a list of users using method GET
    When