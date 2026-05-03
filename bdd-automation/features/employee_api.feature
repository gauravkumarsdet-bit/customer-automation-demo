Feature: Employee API and DB validation

  @api @db
  Scenario: Verify employee API response with database
    When I send GET request to employee API
    Then employee API response status should be 200
    And employee API response should contain employee records
    And first employee from API should exist in database
    And first employee API data should match with database

