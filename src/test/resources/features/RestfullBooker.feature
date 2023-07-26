@JiraXray-[TestSet]
Feature: Restful Booker API

  @JiraXray-[Test] @Authorization @CP001
  Scenario: [POST][200] Authorizathion create token
    Given Josimar registered on the API
    When Josimar access POST the API "/auth" request endpoint token the following body in the request:
      | username | password    |
      | admin    | password123 |
    Then Verify the response status code 200


  @JiraXray-[Test] @Authorization @CP002
  Scenario: [GET][200] Get Bookind ID
    Given Josimar registered on the API
    And John includes the following path params in the request:
      | id | 1 |
    When Josimar access GET the API "/booking/{id}" request endpoint the following body in the request:
    Then Verify the response status code 200

  @JiraXray-[Test] @Authorization @CP003
  Scenario: [GET][200] Get Bookind by name
    Given Josimar registered on the API
    And John includes the following query params in the request:
      | firstname | Mark   |
      | lastname  | Wilson |
    When Josimar access GET the API "/booking" request endpoint the following body in the request:
    Then Verify the response status code 200

  @JiraXray-[Test] @Authorization @CP004
  Scenario: [GET][200] Get Bookind by filter date
    Given Josimar registered on the API
    And John includes the following query params in the request:
      | checkin  | 2023-03-13 |
      | checkout | 2023-05-21 |
    When Josimar access GET the API "/booking" request endpoint the following body in the request:
    Then Verify the response status code 200


  @JiraXray-[Test] @Authorization @CP005
  Scenario: [POST][200] Create Bookind
    Given Josimar registered on the API
    When Josimar access POST the API "/booking" request endpoint the following body in the request:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Jim       | Brown    | 111        | true        | 2022-12-01 | 2022-12-04 | Breakfast       |
    Then Verify the response status code 200
