Feature: Auth
  As an auth folder
  I want to see api alta shop
  So that I can be access all endpoints folder auth register,login and get info


  Scenario: Login - Login success with data valid
    Given User set api POST request login
    When User send request body login
    Then User receive response code 200
    And User receive response body success

  Scenario: Login - Login failed username valid but password invalid
    Given User set api POST request login
    When User send request body login failed username valid but password invalid
    Then User receive response code 400
    And User receive response body error record not found

  Scenario: Login - Login failed not registered
    Given User set api POST request login
    When User send request body login not registered
    Then User receive response code 400
    And User receive response body error not registered

  Scenario: Login - Login failed all empty
    Given User set api POST request login
    When User send request body login failed all empty
    Then User receive response code 400
    And User receive response body error email is required

  Scenario: Login - Login failed email empty
    Given User set api POST request login
    When User send request body login failed email empty
    Then User receive response code 400
    And User receive response body error email is required

  Scenario: Login - Login failed password empty
    Given User set api POST request login
    When User send request body login failed password empty
    Then User receive response code 400
    And User receive response body error password is required

  Scenario: Register - Register Success with data valid
    Given User set api POST request register
    When User send request body register success
    Then User receive response code 200
    And User receive response body success register

  Scenario: Register - Register failed with all data empty
    Given User set api POST request register
    When User send request body register all empty
    Then User receive response code 400
    And User receive response body error register form required

  Scenario: Register - Register failed with email empty
    Given User set api POST request register
    When User send request body register email empty
    Then User receive response code 400
    And User receive response body error register form required

  Scenario: Register - Register failed with fullname empty
    Given User set api POST request register
    When User send request body register fullname empty
    Then User receive response code 400
    And User receive response body error register form fullname required

  Scenario: Register - Register failed with password empty
    Given User set api POST request register
    When User send request body register password empty
    Then User receive response code 400
    And User receive response body error register form password required

  Scenario: Register - Register already exist
    Given User set api POST request register
    When User send request body register already exist
    Then User receive response code 400
    And User receive response body error register already exist

  Scenario: Get Information - Get Information with token valid
    Given User set api Get request Get Information user
    When User send request url get information
    Then User receive response code 200
    And User receive response body success get information

  Scenario: Get Information - Get Information with token empty
    Given User set api Get request Get Information user
    When User send request url get information token empty
    Then User receive response code 401
    And User receive response body failed get information token empty

  Scenario: Get Information - Get Information with token invalid
    Given User set api Get request Get Information user
    When User send request url get information token invalid
    Then User receive response code 401
    And User receive response body success get information token invalid