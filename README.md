# Customer Automation Demo

A Spring Boot application for managing transactions with login authentication and dynamic month-based filtering.

## This line is from main branch

## Project Structure

```
customer-automation-demo/
├── app/                          # Spring Boot Application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/customerautomation/
│   │   │   │   ├── controller/       # Controllers (Login, Pages, API)
│   │   │   │   ├── entity/           # JPA Entities
│   │   │   │   ├── repository/       # Data Access Layer
│   │   │   │   ├── service/          # Business Logic
│   │   │   │   ├── config/           # Configuration Classes
│   │   │   │   └── CustomerAutomationApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── templates/        # Thymeleaf Templates
│   │   └── test/
│   └── pom.xml
├── automation/                   # (For BDD tests)
├── sql/                          # Database scripts
├── Jenkinsfile                   # CI/CD Pipeline
└── README.md
```
## /automation - BDD framework is added.
- UI + API + DB automation
- Jenkins ready

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Web**
- **Spring Data JPA**
- **MySQL 8.0**
- **Thymeleaf**
- **Maven**

## Prerequisites

- JDK 17+
- MySQL 8.0+
- Maven 3.6+

## Database Setup

Create a MySQL database:
```sql
CREATE DATABASE customer_automation;
USE customer_automation;
```

Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/customer_automation
spring.datasource.username=root
spring.datasource.password=your_password
```

## Running the Application

1. Navigate to the app directory:
   ```bash
   cd app
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the application:
   - Click on http://localhost:8080/login

## Default Credentials

- **Username:** admin
- **Password:** admin123

## Features

### 1. Login System
- Simple login page with hardcoded credentials (admin/admin123)
- Session management
- Logout functionality

### 2. Home Page
- Dashboard with welcome message
- Left sidebar navigation
- Links to Transactions and Profile pages

### 3. Transactions Management
- View all transactions filtered by month
- Display transaction details (ID, Description, Amount, Date, Month)
- Calculate and display total amount for selected month
- Dropdown selector for month filtering

### 4. REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transactions` | Create a new transaction |
| GET | `/api/transactions` | Get all transactions |
| GET | `/api/transactions/month/{month}` | Get transactions by month |

### 5. Database Schema

**transactions table:**
```sql
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    date DATE NOT NULL,
    month VARCHAR(20) NOT NULL
);
```

## Sample Data

The application automatically loads sample data on startup:
- January: 3 transactions
- February: 3 transactions
- March: 3 transactions
- April: 2 transactions

## Validation Rules

- **Amount:** Cannot be null
- **Description:** Cannot be empty

## Project Architecture

### Layered Architecture

```
Controller (Web Layer)
    ↓
Service (Business Logic)
    ↓
Repository (Data Access)
    ↓
Database
```

## File Structure

**Controllers:**
- `LoginController.java` - Handles login/logout operations
- `PageController.java` - Serves HTML pages
- `TransactionApiController.java` - REST API endpoints

**Entity:**
- `Transaction.java` - JPA entity for transactions table

**Service:**
- `TransactionService.java` - Business logic for transactions

**Repository:**
- `TransactionRepository.java` - Data access interface

**Templates (Thymeleaf):**
- `login.html` - Login page
- `home.html` - Dashboard page
- `transactions.html` - Transactions page with filtering
- `profile.html` - User profile page

**Configuration:**
- `DataInitializer.java` - Initializes sample data on startup

## Build with Maven

```bash
# Clean and build
mvn clean install

# Run tests
mvn test

# Generate JAR
mvn package

# Deploy
java -jar target/customer-automation-1.0.0.jar
```

## Troubleshooting

### Database Connection Error
- Ensure MySQL is running on localhost:3306
- Check credentials in `application.properties`
- Verify database `customer_automation` exists

### Port Already in Use
- Change port in `application.properties`: `server.port=8081`

### Build Fails
- Clear Maven cache: `mvn clean`
- Check Java version: `java -version` (should be 17+)

## Future Enhancements

- [ ] Spring Security for authentication
- [ ] User registration and management
- [ ] Edit/Delete transaction functionality
- [ ] Transaction export to CSV/PDF
- [ ] Dashboard analytics and charts
- [ ] Unit and integration tests
- [ ] Docker containerization
- [ ] Deployment to cloud platforms

## License

This project is for demonstration purposes.

## Support

For issues or questions, refer to the project structure and code comments.
