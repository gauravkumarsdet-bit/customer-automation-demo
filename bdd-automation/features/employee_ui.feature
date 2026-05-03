Feature: Employee Page UI validation

@ui
Scenario: Verify employee page loads successfully
    Given I login to the application
    When I click on the employee tab
    Then I should see employee table

