# Automation Framework - Quick Start Guide

## 📋 Prerequisites

Before running the tests, ensure:

1. **Spring Boot App is Running**
   ```bash
   cd c:\MyFILES\Study\customer-automation-demo\app
   mvn spring-boot:run
   ```
   - Should be accessible at: http://localhost:8080/login

2. **MySQL is Running on Port 3307**
   - Database: `customer_automation`
   - Username: `root`
   - Password: `admin123`

3. **Java 17+ Installed**
   ```bash
   java -version
   ```

4. **Maven Installed**
   ```bash
   mvn -version
   ```

## 🚀 Quick Start

### Step 1: Navigate to Automation Directory

```bash
cd c:\MyFILES\Study\customer-automation-demo\automation
```

### Step 2: Build the Project

```bash
mvn clean install
```

### Step 3: Run All Tests

```bash
mvn clean test
```

### Step 4: View Reports

After tests complete, open:
- `target/cucumber-reports/cucumber.html` in your browser

## 🎯 Common Commands

| Command | Purpose |
|---------|---------|
| `mvn clean test` | Run all tests |
| `mvn clean test -Dbrowser=firefox` | Run tests in Firefox |
| `mvn clean test -Dbrowser=edge` | Run tests in Edge |
| `mvn test -Dtest=TestNGRunner` | Run specific runner |
| `mvn clean install` | Build & install |
| `mvn dependency:tree` | View dependencies |

## 📊 Test Structure

```
Feature: Login and Transactions Management

  ✓ Scenario: User Login and Navigate to Transactions
  ✓ Scenario: View Transactions for January
  ✓ Scenario: API - Get All Transactions
  ✓ Scenario: API - Get Transactions by Month
```

## 🔧 Configuration

Edit `src/test/resources/config/config.properties` to change:

```properties
# Browser type
browser=chrome

# Application URL
app.url=http://localhost:8080

# MySQL connection
db.url=jdbc:mysql://localhost:3307/customer_automation
db.username=root
db.password=admin123

# Wait times (seconds)
implicit.wait=10
explicit.wait=20
```

## 📁 Key Files

| File | Purpose |
|------|---------|
| `pom.xml` | Dependencies & build config |
| `config.properties` | Application configuration |
| `login_transactions.feature` | Test scenarios |
| `LoginTransactionSteps.java` | Step implementations |
| `TestNGRunner.java` | Test runner |
| `LoginPage.java` | Login page POM |
| `HomePage.java` | Home page POM |
| `TransactionsPage.java` | Transactions page POM |
| `TransactionApiClient.java` | REST API client |
| `DBUtils.java` | Database utilities |

## ✅ Verify Setup

Before running tests, verify:

1. **Spring Boot Running**
   ```bash
   curl http://localhost:8080/login
   ```
   Should return HTML (not error)

2. **MySQL Connected**
   ```bash
   mysql -u root -P 3307 -p
   # Enter password: admin123
   SHOW DATABASES;
   EXIT;
   ```
   Should show `customer_automation` database

3. **Maven Working**
   ```bash
   mvn -version
   ```
   Should show Maven version

## 🐛 Troubleshooting

### Build Fails: Dependency Not Found
```bash
mvn clean
mvn dependency:resolve
mvn clean install
```

### Tests Fail with Connection Error
- Check Spring Boot app is running
- Check MySQL is running on port 3307
- Verify config.properties has correct credentials

### Tests Timeout
- Increase wait times in config.properties
- Ensure application UI is responsive
- Check network connectivity

### Browser Driver Not Found
```bash
mvn clean install
```
WebDriverManager will auto-download drivers

## 📈 Test Reports

After execution:

1. **HTML Report**
   ```
   automation/target/cucumber-reports/cucumber.html
   ```
   - Open in browser for visual report

2. **Console Output**
   - View in terminal/IDE console
   - Shows each step execution

3. **JUnit XML**
   ```
   automation/target/cucumber-reports/cucumber.xml
   ```
   - For CI/CD integration

## 🎓 Examples

### Run Tests with Firefox

```bash
mvn clean test -Dbrowser=firefox
```

### Run Specific Test Scenario

Edit `login_transactions.feature` to tag scenario:
```gherkin
@smoke
Scenario: User Login and Navigate to Transactions
```

Then run:
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

### Enable Dry Run (Parse Only)

```bash
# Edit TestNGRunner.java:
# Change: dryRun = false
# To: dryRun = true

mvn clean test
```

## 📞 Next Steps

1. ✅ Verify prerequisites are met
2. ✅ Run `mvn clean test`
3. ✅ Open cucumber.html report
4. ✅ Review failed scenarios (if any)
5. ✅ Add more test scenarios
6. ✅ Extend with API tests
7. ✅ Add database verification tests

## 🎯 Features Included

✅ **UI Automation** - Selenium WebDriver  
✅ **BDD Scenarios** - Cucumber  
✅ **Test Runner** - TestNG  
✅ **API Testing** - REST Assured  
✅ **Database Testing** - JDBC  
✅ **Page Objects** - POM pattern  
✅ **Configuration** - Externalized config.properties  
✅ **Reporting** - HTML & XML reports  

---

**Happy Testing!** 🚀

For detailed documentation, see: `automation/README.md`
