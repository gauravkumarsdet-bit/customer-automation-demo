Feature: Login and Transactions Management

  Scenario: User Login and Navigate to Transactions
    Given User navigates to login page
    When User enters valid username "admin"
    And User enters valid password "admin123"
    And User clicks login button
    Then User should be redirected to home page
    And Home page should be displayed

  Scenario: View Transactions for January
    Given User is logged in
    When User navigates to transactions page
    And User selects month "January"
    And User clicks load transactions button
    Then Transactions table should be visible
    And Total amount should be displayed
    And Transaction count should be greater than 0

  Scenario: API - Get All Transactions
    Given User has API access to transactions
    When User makes GET request to fetch all transactions
    Then Response status code should be 200
    And Response should contain transaction list

  Scenario: API - Get Transactions by Month
    Given User has API access to transactions
    When User makes GET request to fetch transactions for "February"
    Then Response status code should be 200
    And Response should contain transactions for "February"
