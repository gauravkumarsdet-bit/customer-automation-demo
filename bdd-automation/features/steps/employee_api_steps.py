from behave import when, then
from utils.api_client import APIClient
from utils.db_utils import DBUtils
from utils.config import EMPLOYEE_API_URL


@when("I send GET request to employee API")
def step_send_get_employee_api(context):
    print(f"Sending GET request to {EMPLOYEE_API_URL}")
    context.api_client = APIClient()
    context.response = context.api_client.get(EMPLOYEE_API_URL)


@then("employee API response status should be 200")
def step_verify_status_code(context):
    assert context.response.status_code == 200, (
        f"Expected status code 200 but got {context.response.status_code}"
    )


@then("employee API response should contain employee records")
def step_verify_employee_records(context):
    response_data = context.response.json()

    assert len(response_data) > 0, "Employee API returned empty response"

    context.first_employee = response_data[0]
    print("First Employee:", context.first_employee)
    print("Available keys:", context.first_employee.keys())


@then("first employee from API should exist in database")
def step_verify_employee_exists_in_db(context):
    employee_id = context.first_employee["empId"]

    db = DBUtils()
    context.db_employee = db.get_employee_by_id(employee_id)
    db.close_connection()

    assert context.db_employee is not None, f"Employee ID {employee_id} not found in database"


@then("first employee API data should match with database")
def step_verify_api_data_matches_db(context):
    api_employee = context.first_employee
    db_employee = context.db_employee

    assert api_employee["empId"] == db_employee["emp_id"], \
        f"Emp ID mismatch. API: {api_employee['empId']}, DB: {db_employee['emp_id']}"

    assert api_employee["salary"] == db_employee["salary"], \
        f"Salary mismatch. API: {api_employee['salary']}, DB: {db_employee['salary']}"

    assert api_employee["city"] == db_employee["city"], \
        f"City mismatch. API: {api_employee['city']}, DB: {db_employee['city']}"

    assert api_employee["state"] == db_employee["state"], \
        f"State mismatch. API: {api_employee['state']}, DB: {db_employee['state']}"