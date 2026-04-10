package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import com.example.utils.ConfigReader;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By dashboardTab = By.linkText("Dashboard");
    private By transactionsTab = By.linkText("Transactions");
    private By profileTab = By.linkText("Profile");
    private By welcomeMessage = By.xpath("//h1[contains(text(), 'Welcome')]");
    private By logoutLink = By.linkText("Logout");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    public boolean isHomePageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        try {
            WebElement welcome = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
            return welcome.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickTransactionsTab() {
        WebElement transactions = wait.until(ExpectedConditions.elementToBeClickable(transactionsTab));
        transactions.click();
        System.out.println("Clicked Transactions tab");
    }

    public void clickDashboardTab() {
        WebElement dashboard = wait.until(ExpectedConditions.elementToBeClickable(dashboardTab));
        dashboard.click();
        System.out.println("Clicked Dashboard tab");
    }

    public void clickProfileTab() {
        WebElement profile = wait.until(ExpectedConditions.elementToBeClickable(profileTab));
        profile.click();
        System.out.println("Clicked Profile tab");
    }

    public void clickLogout() {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logout.click();
        System.out.println("Clicked Logout");
    }

    public boolean isLoaded() {
        return isHomePageDisplayed();
    }
}
