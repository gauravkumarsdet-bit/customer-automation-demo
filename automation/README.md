# Automation Framework - Customer Automation Demo

A comprehensive **Selenium + Cucumber + TestNG + REST Assured** automation framework for testing the Spring Boot Customer Automation application.

## 📁 Project Structure

```
automation/
├── pom.xml                                  # Maven configuration
│
├── src/test/
│   ├── java/com/example/
│   │   ├── runners/
│   │   │   └── TestNGRunner.java           # Cucumber TestNG runner
│   │   ├── stepdefinitions/
│   │   │   └── LoginTransactionSteps.java  # BDD step implementations
│   │   ├── pages/
│   │   │   ├── LoginPage.java              # Login page object
│   │   │   ├── HomePage.java               # Home page object
│   │   │   └── TransactionsPage.java       # Transactions page object
│   │   ├── api/
│   │   │   └── TransactionApiClient.java   # REST API client
│   │   ├── db/
│   │   │   └── DBUtils.java                # Database utilities
│   │   ├── utils/
│   │   │   ├── ConfigReader.java           # Configuration reader
│   │   │   └── DriverFactory.java          # WebDriver initialization
│   │   └── hooks/
│   │       └── Hooks.java                  # Setup/teardown hooks
│   │
│   └── resources/
│       ├── config/
│       │   └── config.properties           # Configuration file
│       ├── features/
│       │   └── login_transactions.feature  # Cucumber scenarios
│       └── testng.xml                      # TestNG configuration
└── target/                                  # Build output
```

## /automation  - BDD framework is added.
- UI + API + DB automation
- Jenkins ready

## 🛠 Technologies

| Component | Version |
|-----------|---------|
| Java | 17 |
| Selenium WebDriver | 4.15.0 |
| Cucumber | 7.14.0 |
| TestNG | 7.8.1 |
| REST Assured | 5.3.2 |
| WebDriverManager | 5.6.3 |
| MySQL JDBC | 8.0.33 |

## ⚙️ Configuration

### config.properties

Located at: `src/test/resources/config/config.properties`

```properties
# Application URL
app.url=http://localhost:8080

# Browser (chrome, firefox, edge)
browser=chrome

# Timeouts (in seconds)
implicit.wait=10
explicit.wait=20

# MySQL Database
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3307/customer_automation
db.username=root
db.password=admin123

# Test Data
test.username=admin
test.password=admin123
```

## 🏗 Framework Components

### 1. **Page Object Model (POM)**
- `LoginPage.java` - Login functionality
- `HomePage.java` - Dashboard navigation
- `TransactionsPage.java` - Transactions management

### 2. **Step Definitions**
- `LoginTransactionSteps.java` - BDD step implementations

### 3. **API Testing**
- `TransactionApiClient.java` - REST API client methods
  - `createTransaction()`
  - `getAllTransactions()`
  - `getTransactionsByMonth()`

### 4. **Database Layer**
- `DBUtils.java` - Database operations
  - `executeQuery()`
  - `executeUpdate()`
  - `getTransactionCount()`
  - `getTotalAmount()`

### 5. **Utilities**
- `ConfigReader.java` - Reads configuration properties
- `DriverFactory.java` - WebDriver initialization and management

### 6. **Hooks**
- `Hooks.java` - Setup/teardown for each scenario

## 📋 Feature Files

### login_transactions.feature

Contains scenarios for:
1. User login and navigation to transactions
2. View transactions by month
3. API test - Get all transactions
4. API test - Get transactions by month

## 🚀 Running Tests

### Prerequisites

1. **Spring Boot App Running**
   ```bash
   cd app
   mvn spring-boot:run
   ```
   - Accessible at: http://localhost:8080

2. **MySQL Running**
   - Database: `customer_automation`
   - User: `root`
   - Password: `admin123`
   - Port: `3307`

3. **Java 17+ Installed**
   ```bash
   java -version
   ```

### Run All Tests

```bash
cd automation
mvn clean test
```

### Run Specific Test

```bash
mvn -Dtest=TestNGRunner test
```

### Run with Browser Option

```bash
mvn clean test -Dbrowser=firefox
```

Supported browsers: `chrome`, `firefox`, `edge`

## 📊 Test Reports

After test execution:

- **Cucumber HTML Report**: `target/cucumber-reports/cucumber.html`
- **Cucumber JSON**: `target/cucumber-reports/cucumber.json`
- **JUnit XML**: `target/cucumber-reports/cucumber.xml`

Open the HTML report in a browser to view detailed test results.

## 🔍 Feature Scenarios

### Scenario 1: User Login
```gherkin
Given User navigates to login page
When User enters valid username "admin"
And User enters valid password "admin123"
And User clicks login button
Then User should be redirected to home page
And Home page should be displayed
```

### Scenario 2: View Transactions
```gherkin
Given User is logged in
When User navigates to transactions page
And User selects month "January"
And User clicks load transactions button
Then Transactions table should be visible
And Total amount should be displayed
```

### Scenario 3: API - Get All Transactions
```gherkin
Given User has API access to transactions
When User makes GET request to fetch all transactions
Then Response status code should be 200
And Response should contain transaction list
```

## 🔧 Extending the Framework

### Add New Page Object

Create a new file in `src/test/java/com/example/pages/`:

```java
package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewPage {
    private WebDriver driver;
    private By element = By.id("elementId");
    
    public NewPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void performAction() {
        // Implementation
    }
}
```

### Add New Step Definition

Add methods to `LoginTransactionSteps.java`:

```java
@Given("User performs some action")
public void user_performs_action() {
    // Step implementation
}
```

### Add New Feature Scenario

Edit `login_transactions.feature`:

```gherkin
Scenario: New test scenario
    Given precondition
    When action
    Then result validation
```

## 🐛 Troubleshooting

### Browser Not Found
- Solution: Update WebDriverManager or install browser driver

### MySQL Connection Error
- Check MySQL is running on port 3307
- Verify credentials in config.properties

### Timeout Exceptions
- Increase wait times in config.properties
- Check if application is running

### Feature Not Found
- Ensure features are in `src/test/resources/features`
- Check glue path in TestNGRunner

## 📝 Best Practices

1. **POM Pattern** - Keep page elements and actions separate
2. **DRY Principle** - Reuse page objects and utilities
3. **Configuration** - Use config.properties for settings
4. **Explicit Waits** - Use WebDriverWait instead of Thread.sleep()
5. **Assertions** - Use meaningful assertion messages
6. **Hooks** - Use for common setup/teardown

## 🎯 Test Execution Flow

```
1. Hook @Before
   ↓
2. Feature scenario starts
   ↓
3. Step definitions execute
   ↓
4. Page objects interact with UI
   ↓
5. API client makes requests
   ↓
6. DB utilities query database
   ↓
7. Assertions validate results
   ↓
8. Hook @After (cleanup)
```

## 📚 Dependencies

| Dependency | Purpose |
|-----------|---------|
| Selenium | Browser automation |
| Cucumber | BDD framework |
| TestNG | Test execution |
| REST Assured | API testing |
| WebDriverManager | Driver management |
| MySQL JDBC | Database connectivity |
| AssertJ | Assertions |
| SLF4J | Logging |

## ✅ Checklist

- ✅ Page Object Model implemented
- ✅ Cucumber BDD scenarios created
- ✅ REST API client ready
- ✅ Database utilities configured
- ✅ Configuration externalized
- ✅ TestNG runner configured
- ✅ Hooks for setup/teardown
- ✅ Logging enabled
- ✅ Reports generated

## 🚀 Next Steps

1. Run tests: `mvn clean test`
2. Review cucumber reports
3. Add more feature scenarios
4. Implement additional page objects
5. Increase test coverage

## 📞 Support

Refer to code comments and feature files for detailed implementation.

---

Happy Testing! 🎉
