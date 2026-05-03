Feature: Employee Page UI validation

@ui
Scenario: Verify employee page loads successfully
    Given I login to the application
    When I click on the employee tab
    Then I should see employee table

  @ui @db
  Scenario: Verify employee city in UI table with database
    Given I login to the application
    When I click on the employee tab
    Then city values in employee table should match with database using emp id

