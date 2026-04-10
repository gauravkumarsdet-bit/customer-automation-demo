# Quick Start Guide

## Prerequisites

1. **Install Java 17+**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify: `java -version`

2. **Install Maven 3.6+**
   - Download from: https://maven.apache.org/download.cgi
   - Verify: `mvn -version`

3. **Install MySQL 8.0+**
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Start MySQL service

## Step 1: Create Database

Open MySQL command line or MySQL Workbench:

```sql
CREATE DATABASE customer_automation;
USE customer_automation;
```

Or run the SQL script:
```bash
mysql -u root -p < sql/schema.sql
```

## Step 2: Update Database Configuration

Edit `app/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/customer_automation
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

## Step 3: Build the Application

```bash
cd app
mvn clean install
```

## Step 4: Run the Application

**Option 1 - Using Maven:**
```bash
mvn spring-boot:run
```

**Option 2 - Running JAR directly:**
```bash
mvn clean package
java -jar target/customer-automation-1.0.0.jar
```

## Step 5: Access the Application

1. Open browser and navigate to: **http://localhost:8080/login**
2. Login with credentials:
   - Username: **admin**
   - Password: **admin123**

## Troubleshooting

### MySQL Connection Refused
- Ensure MySQL is running: `mysql -u root -p` (should open MySQL prompt)
- Check port 3306 is accessible
- Verify credentials in application.properties

### Maven Build Failure
```bash
# Clear Maven cache
mvn clean

# Update dependencies
mvn update-snapshots

# Try build again
mvn clean install
```

### Port 8080 Already in Use
Edit `app/src/main/resources/application.properties`:
```properties
server.port=8081
```

## Testing the APIs

### Get All Transactions
```bash
curl http://localhost:8080/api/transactions
```

### Get Transactions by Month
```bash
curl http://localhost:8080/api/transactions/month/January
```

### Create a Transaction
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Test Transaction",
    "amount": 100.00,
    "date": "2026-01-15",
    "month": "January"
  }'
```

## Application Structure

```
app/
├── src/main/java/com/example/customerautomation/
│   ├── controller/
│   │   ├── LoginController.java      (Login/Logout)
│   │   ├── PageController.java       (Web Pages)
│   │   └── TransactionApiController.java  (REST APIs)
│   ├── entity/
│   │   └── Transaction.java          (Database Entity)
│   ├── repository/
│   │   └── TransactionRepository.java (Data Access)
│   ├── service/
│   │   └── TransactionService.java   (Business Logic)
│   ├── config/
│   │   └── DataInitializer.java      (Sample Data)
│   └── CustomerAutomationApplication.java
├── resources/
│   ├── templates/
│   │   ├── login.html
│   │   ├── home.html
│   │   ├── transactions.html
│   │   └── profile.html
│   └── application.properties
└── pom.xml
```

## Features

✅ **Login System** - Hardcoded admin user  
✅ **Dashboard** - Home page with navigation  
✅ **Transactions** - View by month with filtering  
✅ **REST APIs** - Full CRUD operations  
✅ **Auto Sample Data** - Loaded on startup  
✅ **Responsive UI** - Clean and simple design  
✅ **Session Management** - Login persistence  

## Next Steps

1. ✅ Run the application
2. ✅ Test login functionality
3. ✅ Explore transactions by month
4. ✅ Test REST APIs
5. ✅ Review code structure
6. ✅ Customize as needed

## Support

For questions, review the code comments and README.md file.

Happy coding! 🚀
