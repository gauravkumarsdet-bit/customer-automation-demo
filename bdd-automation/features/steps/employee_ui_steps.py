from behave import given, when, then
from pages.employee_page import EmployeePage
from utils.config import BASE_URL


@given("I login to the application")
def step_open_application(context):
    context.employee_page = EmployeePage(context.driver)
    context.employee_page.open_application(BASE_URL)
    context.employee_page.login_application("admin", "admin123")

@when("I click on the employee tab")
def step_click_employee_tab(context):
    context.employee_page.click_employee_tab()

@then("I should see employee table")
def step_verify_employee_table(context):
    assert context.employee_page.is_employee_table_visible(), "Employee table is not visible"

@then("city values in employee table should match with database using emp id")
def step_verify_city_values_with_database(context):
    context.employee_page.verify_city_values_with_database()