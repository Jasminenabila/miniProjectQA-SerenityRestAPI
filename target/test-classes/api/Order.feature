Feature: Order
  As an order folder
  I want to see api alta shop
  So that I can be access all endpoints order products

  Scenario: Add Order - Add Orders with data valid and token valid
    Given User create product and login before to get product id
    When User send POST api request orders data valid and token valid
    Then User receive valid get response code 200 orders data valid and token valid
    And User receive valid get response body for orders data valid and token valid

  Scenario: Add Order - Add Orders with data valid and token empty
    Given User send POST api request orders data valid and token empty
    When User receive valid get response code 401 orders token empty
    Then User receive valid get response body for orders token empty

  Scenario: Add Order - Add Orders with product id input string
    Given User create product and login before to get product id
    When User send POST api request orders data product id input string
    Then User receive valid get response code 400 product id
    And User receive valid get response body error for orders data product id

  Scenario: Add Order - Add Orders with product id input empty
    Given User create product and login before to get product id
    When User send POST api request orders data product id input empty
    Then User receive valid get response code 400 product id
    And User receive valid get response body error for orders data product id

  Scenario: Add Order - Add Orders with quantity input minus
    Given User create product and login before to get product id
    When User send POST api request orders data quantity input minus
    Then User receive valid get response code 400 quantity
    And User receive valid get response body error for quantity

  Scenario: Add Order - Add Orders with quantity input empty
    Given User create product and login before to get product id
    When User send POST api request orders data quantity input empty
    Then User receive valid get response code 400 quantity
    And User receive valid get response body error for quantity

  Scenario: Get Order - Get Orders with token valid
    Given User create product and login before to get product id
    When User send GET api request order token valid
    Then User receive valid get response code 200 token valid get orders
    And User receive valid get response body for get orders token valid

  Scenario: Get Orders - Get Orders with token invalid
    Given User create product and login before to get product id
    When User send GET api request orders with token invalid
    Then User receive valid get response code 401 orders token invalid
    And User receive valid get response body for get orders token invalid

  Scenario: Get Orders By Id - Get Orders By Id with id found
    Given User create orders and login before to get order id
    When User Get Order by id found with token valid
    Then User receive valid get response code 200 get orders by id
    And User receive valid get response body for get orders by id

  Scenario: Get Orders by Id - Get Orders by Id with token empty
    Given User send GET api request orders by id token empty
    When User receive valid get response code 401 orders token empty
    Then User receive valid get response body error for token empty

  Scenario: Get Orders by Id - Get Orders by Id with id not found
    Given User set api url orders by id not found
    When User send GET api request orders by id not found
    And User receive valid get response code 404 orders id not found
    Then User receive valid get response body error for id not found get orders
