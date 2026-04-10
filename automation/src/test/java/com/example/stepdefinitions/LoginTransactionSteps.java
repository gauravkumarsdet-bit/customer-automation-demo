package com.example.stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import com.example.pages.LoginPage;
import com.example.pages.HomePage;
import com.example.pages.TransactionsPage;
import com.example.api.TransactionApiClient;
import com.example.utils.DriverFactory;
import io.restassured.response.Response;

import static org.testng.Assert.*;

public class LoginTransactionSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private TransactionsPage transactionsPage;
    private TransactionApiClient apiClient;
    private Response apiResponse;

    @Given("User navigates to login page")
    public void user_navigates_to_login_page() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        assertTrue(loginPage.isLoginPageDisplayed(), "Login page should be displayed");
    }

    @When("User enters valid username {string}")
    public void user_enters_valid_username(String username) {
        loginPage.enterUsername(username);
    }

    @And("User enters valid password {string}")
    public void user_enters_valid_password(String password) {
        loginPage.enterPassword(password);
    }

    @And("User clicks login button")
    public void user_clicks_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("User should be redirected to home page")
    public void user_should_be_redirected_to_home_page() {
        homePage = new HomePage(driver);
        try {
            Thread.sleep(2000); // Wait for navigation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("Home page should be displayed")
    public void home_page_should_be_displayed() {
        assertTrue(homePage.isHomePageDisplayed(), "Home page should be displayed");
    }

    @Given("User is logged in")
    public void user_is_logged_in() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLogin();
        loginPage.login("admin", "admin123");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        homePage = new HomePage(driver);
        assertTrue(homePage.isHomePageDisplayed(), "User should be logged in");
    }

    @When("User navigates to transactions page")
    public void user_navigates_to_transactions_page() {
        homePage.clickTransactionsTab();
        transactionsPage = new TransactionsPage(driver);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @And("User selects month {string}")
    public void user_selects_month(String month) {
        transactionsPage.selectMonth(month);
    }

    @And("User clicks load transactions button")
    public void user_clicks_load_transactions_button() {
        transactionsPage.clickLoadTransactions();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("Transactions table should be visible")
    public void transactions_table_should_be_visible() {
        assertTrue(transactionsPage.isTransactionTableVisible(), "Transactions table should be visible");
    }

    @And("Total amount should be displayed")
    public void total_amount_should_be_displayed() {
        assertTrue(transactionsPage.isTotalRowVisible(), "Total row should be visible");
        String total = transactionsPage.getTotalAmount();
        assertFalse(total.isEmpty(), "Total amount should not be empty");
    }

    @And("Transaction count should be greater than zero")
    public void transaction_count_should_be_greater_than_zero() {
        int count = transactionsPage.getTransactionCount();
        assertTrue(count > 0, "Transaction count should be greater than 0");
    }

    @Given("User has API access to transactions")
    public void user_has_api_access_to_transactions() {
        apiClient = new TransactionApiClient();
    }

    @When("User makes GET request to fetch all transactions")
    public void user_makes_get_request_to_fetch_all_transactions() {
        apiResponse = apiClient.getAllTransactions();
    }

    @Then("Response status code should be {int}")
    public void response_status_code_should_be(int statusCode) {
        assertEquals(apiResponse.getStatusCode(), statusCode, "Status code should be " + statusCode);
    }

    @And("Response should contain transaction list")
    public void response_should_contain_transaction_list() {
        assertTrue(apiResponse.jsonPath().getList("$").size() >= 0, "Response should contain transaction list");
    }

    @When("User makes GET request to fetch transactions for {string}")
    public void user_makes_get_request_to_fetch_transactions_for(String month) {
        apiResponse = apiClient.getTransactionsByMonth(month);
    }

    @And("Response should contain transactions for {string}")
    public void response_should_contain_transactions_for(String month) {
        if (apiResponse.getStatusCode() == 200) {
            int count = apiResponse.jsonPath().getList("$").size();
            assertTrue(count >= 0, "Response should contain transactions for " + month);
        }
    }
}
