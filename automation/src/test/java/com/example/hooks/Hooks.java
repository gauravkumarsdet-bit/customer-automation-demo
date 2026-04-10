package com.example.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import com.example.utils.DriverFactory;
import com.example.db.DBUtils;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("==== Starting Test Scenario ====");
        WebDriver driver = DriverFactory.initializeDriver();
        System.out.println("Browser initialized: " + DriverFactory.getDriver().getClass().getSimpleName());
    }

    @After
    public void tearDown() {
        System.out.println("==== Ending Test Scenario ====");
        try {
            DriverFactory.quitDriver();
            DBUtils.closeConnection();
            System.out.println("Browser closed and Database connection closed");
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}
