Feature: Categories
  As an categories folder
  I want to see api alta shop
  So that I can be access all endpoints folder products categories

  Scenario: Add Categories - Add Categories with data valid
    Given User set api post request add categories data valid
    When User send post api request add categories data valid
    Then User receive valid get response code 200 add categories
    And User receive valid get response body for add categories

  Scenario: Add Categories - Add Categories with name empty
    Given User set api post request add categories data valid
    When User send post api request add categories name empty
    Then User receive valid get response code 500 add categories name empty
    And User receive valid get response body for add categories name empty

  Scenario: Add Categories - Add Categories with name input integer
    Given User set api post request add categories data valid
    When User send post api request add categories input integer
    Then User receive valid get response code 400 add categories input integer
    And User receive valid get response body for add categories input integer

  Scenario: Get Categories - Get Categories with url valid
    Given User set api get request categories
    When User send GET api request categories
    Then User receive valid get response code 200 categories
    And User receive valid get response body data for categories

  Scenario: Get Categories - Get Categories with url invalid
    Given User set api get request categories url invalid
    When User send GET api request categories with url invalid
    Then User receive valid get response code 404 categories invalid

  Scenario: Get Categories By id - Get Categories with id found
    Given User set api get request categories by id found
    When User send GET api request categories by id found
    Then User receive valid get response code 200 categories by id found
    And User receive valid get response body data for categories by id found

  Scenario: Get Categories By id - Get Categories with id not found
    Given User set api get request categories by id not found
    When User send GET api request categories by id not found
    Then User receive valid get response code 404 categories by id not found
    And User receive valid get response body data for categories by id not found

  Scenario: Get Categories By id - Get Categories with id input string
    Given User set api get request categories by id input string
    When User send GET api request categories by id input string
    Then User receive valid get response code 400 categories by id input string
    And User receive valid get response body data for categories by id input string