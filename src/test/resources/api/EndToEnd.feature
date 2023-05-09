Feature: End To End
  As an user
  I want to running end to end test
  So that I can be access all endpoints alta shop

  Scenario: End to end
    Given User access Auth module folders positive case has been successfully
    When User access Products module folders positive case has been successfully
    And User access Products Categories module folders positive case has been successfully
    Then User access Orders module folders positive case has been successfully