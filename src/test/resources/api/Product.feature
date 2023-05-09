Feature: Product
  As an product folder
  I want to see api alta shop
  So that I can be access all endpoints folder products

  Scenario: Get Products - Get Products with url valid
    Given User set api get request products alta shop valid
    When User send GET api request products with url valid
    Then User receive valid get response code 200 products url valid
    And User receive valid get response body for products url valid

  Scenario: Get Products - Get Products with url invalid
    Given User set api get request products alta shop invalid
    When User send GET api request products with url invalid
    Then User receive valid get response code 404 products url invalid

  Scenario: Add Products - Add Products with data valid
    Given User set api post request add products data valid
    When User send post api request add products with url valid
    Then User receive valid get response code 200 add products data valid
    And User receive valid get response body for add products url valid

  Scenario: Add Products - Add Products name empty
    Given User set api post request add products data valid
    When User send post api request add products name empty
    Then User receive valid get response code 500 add products name empty
    And User receive valid get response body for add products name empty

  Scenario: Add Products - Add Products format name integer
    Given User set api post request add products data valid
    When User send post api request add products name format integer
    Then User receive valid get response code 400 add products invalid format
    And User receive valid get response body for add products invalid format

  Scenario: Add Products - Add Products price empty
    Given User set api post request add products data valid
    When User send post api request add products price empty
    Then User receive valid get response code 400 add products invalid format
    And User receive valid get response body for add products invalid format

  Scenario: Add Products - Add Products price input negative
    Given User set api post request add products data valid
    When User send post api request add products price input negative
    Then User receive valid get response code 400 add products invalid format
    And User receive valid get response body for add products invalid format

  Scenario: Add Products - Add Products price categories empty
    Given User set api post request add products data valid
    When User send post api request add products categories empty
    Then User receive valid get response code 400 add products invalid format
    And User receive valid get response body for add products invalid format

  Scenario: Add Products - Add Products price categories input string
    Given User set api post request add products data valid
    When User send post api request add products categories input string
    Then User receive valid get response code 400 add products invalid format
    And User receive valid get response body for add products invalid format

  Scenario: Get Products By Id - Get Products By Id with id found
    Given User set api get request products by id found
    When User send GET api request products by id found
    Then User receive valid get response code 200 products id found
    And User receive valid get response body for products id found

  Scenario: Get Products By Id - Get Products By Id with id not found
    Given User set api get request products by id not found
    When User send GET api request products by id not found
    Then User receive valid get response code 404 products id not found
    And User receive valid get response body for products id not found

  Scenario: Delete Products By Id - Delete Products By Id with id found
    Given User create valid product before
    When User send delete api request products by id found
    Then User receive api delete valid get response code 200 products id found
    And User receive valid api delete get response body for products id found

  Scenario: Delete Products By Id - Delete Products By Id with id limit maximum
    Given User set api delete request products by id limit max
    When User send delete api request products by id limit max
    Then User receive valid api delete get response code 404 products id limit max
    And User receive valid api delete get response body for products id limit max

  Scenario: Add Ratings - Add Ratings with data valid
    Given User set api post request add ratings url valid
    When User send post api request add ratings with data valid
    Then User receive valid get response code 200 add ratings data valid
    And User receive valid get response body for add ratings url valid

  Scenario: Add Ratings - Add Ratings with data id not found
    Given User set api post request add ratings url not found
    When User send post api request add ratings with data not found
    Then User receive valid get response code 500 add ratings data not found
    And User receive valid get response body for add ratings id not found

  Scenario: Add Ratings - Add Ratings with token empty
    Given User set api post request add ratings url valid
    When User send post api request add ratings with token empty
    Then User receive valid get response code 401 token empty
    And User receive valid get response body for token empty

  Scenario: Get Ratings By Id - Get Ratings By Id with id found
    Given User set api get request ratings by id found
    When User send GET api request ratings by id found
    Then User receive valid get response code 200 ratings
    And User receive valid get response body for ratings

  Scenario: Get Ratings By Id - Get Ratings By Id with id not found
    Given User set api get request ratings by id not found
    When User send GET api request ratings by id not found
    Then User receive valid get response code 200 ratings
    And User receive valid get response body for ratings

  Scenario: Get Ratings By Id - Get Ratings By Id with id input string
    Given User set api get request ratings by id input string
    When User send GET api request ratings by id input string
    Then User receive valid get response code 400 ratings id input string
    And User receive valid get response body for ratings id input string

  Scenario: Add Comments - Add Comments with data and token valid
    Given User set api post request add comments data and token valid
    When User send post api request add comments with data token valid
    Then User receive valid get response code 200 add comments data and token valid
    And User receive valid get response body for add comments data and token valid

  Scenario: Add Comments - Add Comments with token empty
    Given User set api post request add comments token empty
    When User send post api request add comments token empty
    Then User receive valid get response code 401 add comments
    And User receive valid get response body error token empty

  Scenario: Add Comments - Add Comments with content empty
    Given User set api post request add comments data and token valid
    When User send post api request add Comments content empty
    Then User receive valid get response code 500 add comments
    And User receive valid get response body error content empty

  Scenario: Add Comments - Add Comments with input string url
    Given User set api post request add comments input string url
    When User send post api request add comments with data input string url
    Then User receive valid get response code 400 add comments
    And User receive valid get response body error input string url

  Scenario: Get Comments - Get Comments with url valid
    Given User set api get request comments url valid
    When User send GET api request comments with url valid
    Then User receive valid get response code 200 comments
    And User receive valid get response body for comments url valid

  Scenario: Get Comments - Get Comments with url invalid
    Given User set api get request comments url invalid
    When User send GET api request comments with url invalid
    Then User receive valid get response code 200 comments
    And User receive valid get response body for comments id or url not found