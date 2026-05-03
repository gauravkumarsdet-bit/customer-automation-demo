# Employees Feature Documentation

## Launch the local web app

From the project root:

```bash
cd app
mvn spring-boot:run
```

Or build the jar and run it:

```bash
cd app
mvn -DskipTests package
java -jar target/customer-automation-1.0.0.jar
```

The app will start on `http://localhost:8080`.

---

## Employees feature overview

A new Employees feature has been added with a separate UI page and full backend API support.

### UI page

- New sidebar menu item: `Employees`
- The page loads employee data from the database via `GET /api/employees`
- Supports:
  - Add Employee
  - Edit Employee
  - Delete Employee
  - Refresh Table

### Backend APIs

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/api/employees` | Fetch all employees |
| GET | `/api/employees/{empId}` | Fetch one employee by emp_id |
| POST | `/api/employees` | Create a new employee |
| PUT | `/api/employees/{empId}` | Update an existing employee |
| DELETE | `/api/employees/{empId}` | Delete an employee |

### Employee model

Columns in `employees` table:

- `emp_id` INT PRIMARY KEY
- `salary` INT NOT NULL
- `city` VARCHAR(255) NOT NULL
- `state` VARCHAR(255) NOT NULL

---

## SQL script

Run this in MySQL if the table does not already exist:

```sql
CREATE TABLE IF NOT EXISTS employees (
  emp_id INT PRIMARY KEY,
  salary INT NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL
);

INSERT INTO employees (emp_id, salary, city, state) VALUES
(1, 50000, 'Chennai', 'TN'),
(2, 40000, 'Bangalore', 'KA'),
(3, 90000, 'Kolkata', 'WB');
```

---

## API curl commands

### Get all employees

```bash
curl -X GET http://localhost:8080/api/employees
```

### Get employee by ID

```bash
curl -X GET http://localhost:8080/api/employees/2
```

### Create new employee

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"empId":4,"salary":55000,"city":"Mumbai","state":"MH"}'
```

### Update employee

```bash
curl -X PUT http://localhost:8080/api/employees/4 \
  -H "Content-Type: application/json" \
  -d '{"empId":4,"salary":60000,"city":"Mumbai","state":"MH"}'
```

### Delete employee

```bash
curl -X DELETE http://localhost:8080/api/employees/4
```

---

## Use the API in Postman

1. Open Postman.
2. Create a new request.
3. Use `http://localhost:8080/api/employees` as the request URL.
4. Set the HTTP method depending on the action.

### Get all employees
- Method: `GET`
- URL: `http://localhost:8080/api/employees`
- No body required.

### Get employee by ID
- Method: `GET`
- URL: `http://localhost:8080/api/employees/2`
- No body required.

### Create new employee
- Method: `POST`
- URL: `http://localhost:8080/api/employees`
- Headers:
  - `Content-Type: application/json`
- Body type: `raw` JSON
- Body example:
  ```json
  {
    "empId": 4,
    "salary": 55000,
    "city": "Mumbai",
    "state": "MH"
  }
  ```

### Update employee
- Method: `PUT`
- URL: `http://localhost:8080/api/employees/4`
- Headers:
  - `Content-Type: application/json`
- Body type: `raw` JSON
- Body example:
  ```json
  {
    "empId": 4,
    "salary": 60000,
    "city": "Mumbai",
    "state": "MH"
  }
  ```

### Delete employee
- Method: `DELETE`
- URL: `http://localhost:8080/api/employees/4`
- No body required.

> Tip: After a successful `POST`, `PUT`, or `DELETE`, click the `GET /api/employees` request again in Postman to confirm the current list.

---

## Verify data in MySQL

### Show all employees

```sql
SELECT * FROM employees;
```

### Show one employee

```sql
SELECT * FROM employees WHERE emp_id = 2;
```

### Verify changes

- After insert:
  ```sql
  SELECT * FROM employees WHERE emp_id = 4;
  ```
- After update:
  ```sql
  SELECT salary, city, state FROM employees WHERE emp_id = 4;
  ```
- After delete:
  ```sql
  SELECT * FROM employees WHERE emp_id = 4;
  ```

---

## Verify from UI

1. Start the app at `http://localhost:8080`.
2. Login and open the left menu.
3. Click `Employees`.
4. Confirm the table shows the seeded rows:
   - `1 | 50000 | Chennai | TN`
   - `2 | 40000 | Bangalore | KA`
   - `3 | 90000 | Kolkata | WB`
5. Use the `Add Employee` form and click `Add Employee`.
6. Click `Edit` on any row, change values, and save.
7. Click `Delete` on any row.
8. Use `Refresh Table` to reload the latest values from the DB.

---

## Notes

- No existing `transactions` functionality was changed.
- The Employees page is implemented as a separate feature.
- No application.properties change was required for this feature.
