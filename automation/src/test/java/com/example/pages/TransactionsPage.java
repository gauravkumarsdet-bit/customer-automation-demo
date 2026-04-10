package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;
import com.example.utils.ConfigReader;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By monthDropdown = By.id("monthSelect");
    private By loadButton = By.xpath("//button[text()='Load Transactions']");
    private By transactionTable = By.xpath("//table//tbody");
    private By tableRows = By.xpath("//table//tbody//tr[not(contains(@class, 'total-row'))]");
    private By totalRow = By.xpath("//tr[@class='total-row']");
    private By totalAmount = By.xpath("//tr[@class='total-row']//td[last()]");
    private By pageTitle = By.xpath("//h1[contains(text(), 'Transactions')]");

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
    }

    public boolean isTransactionsPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectMonth(String month) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(monthDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(month);
        System.out.println("Selected month: " + month);
    }

    public void clickLoadTransactions() {
        WebElement loadBtn = wait.until(ExpectedConditions.elementToBeClickable(loadButton));
        loadBtn.click();
        System.out.println("Clicked Load Transactions button");
    }

    public void loadTransactionsByMonth(String month) {
        selectMonth(month);
        clickLoadTransactions();
        // Wait for table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTable));
    }

    public int getTransactionCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTransactionTableVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(transactionTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalAmount() {
        try {
            WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount));
            String totalText = total.getText();
            // Extract number from "$xxx.xx" format
            return totalText.replace("$", "").trim();
        } catch (Exception e) {
            return "0";
        }
    }

    public List<WebElement> getTableRows() {
        return driver.findElements(tableRows);
    }

    public String getColumnValue(int rowIndex, int columnIndex) {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            if (rowIndex < rows.size()) {
                WebElement row = rows.get(rowIndex);
                List<WebElement> columns = row.findElements(By.tagName("td"));
                if (columnIndex < columns.size()) {
                    return columns.get(columnIndex).getText();
                }
            }
        } catch (Exception e) {
            System.err.println("Error getting column value: " + e.getMessage());
        }
        return "";
    }

    public boolean isTotalRowVisible() {
        try {
            return driver.findElement(totalRow).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
