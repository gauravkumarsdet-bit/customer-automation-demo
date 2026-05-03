import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from utils.db_utils import DBUtils

class EmployeePage:
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 15)

    USERNAME_INPUT = "username"
    PASSWORD_INPUT = "password"
    LOGIN_BUTTON = "//button[text()='Login']"
    DASHBOARD_LINK = "//a[text()='Dashboard']"
    EMPLOYEE_TABLE = "//div[@class='employees-table']/table"
    EMPLOYEE_TAB = "//a[text()='Employees']"

    def open_application(self, url):
        self.driver.get(url)
        self.wait.until(EC.presence_of_element_located((By.NAME, self.USERNAME_INPUT)))

    def login_application(self, username, password):
        self.wait.until(EC.presence_of_element_located((By.NAME, self.USERNAME_INPUT)))
        self.driver.find_element(By.NAME, self.USERNAME_INPUT).send_keys(username)
        self.driver.find_element(By.NAME, self.PASSWORD_INPUT).send_keys(password)
        self.wait.until(EC.element_to_be_clickable((By.XPATH, self.LOGIN_BUTTON)))
        time.sleep(1)
        self.driver.find_element(By.XPATH, self.LOGIN_BUTTON).click()
        self.wait.until(EC.presence_of_element_located((By.XPATH, self.DASHBOARD_LINK)))

    def click_employee_tab(self):
        self.wait.until(EC.element_to_be_clickable((By.XPATH, self.EMPLOYEE_TAB)))
        self.driver.find_element(By.XPATH, self.EMPLOYEE_TAB).click()
        self.wait.until(EC.presence_of_element_located((By.XPATH, self.EMPLOYEE_TABLE)))

    def is_employee_table_visible(self):
        table = self.wait.until(EC.visibility_of_element_located((By.XPATH, self.EMPLOYEE_TABLE)))
        return table.is_displayed()
    
    def get_employee_id_city_from_table(self):
        self.wait.until(EC.visibility_of_element_located((By.XPATH, self.EMPLOYEE_TABLE)))

        rows = self.driver.find_elements(By.XPATH, "//table/tbody/tr")

        employee_ui_data = []

        for row in rows:
            columns = row.find_elements(By.TAG_NAME, "td")

            if len(columns) == 0:
                continue

            emp_id = columns[0].text.strip()
            city = columns[2].text.strip()

            employee_ui_data.append({
                "emp_id": int(emp_id),
                "city": city
            })

        return employee_ui_data


    def verify_city_values_with_database(self):
        ui_employee_data = self.get_employee_id_city_from_table()

        assert len(ui_employee_data) > 0, "No employee records found in UI table"

        db = DBUtils()

        try:
            for employee in ui_employee_data:
                emp_id = employee["emp_id"]
                ui_city = employee["city"]

                db_record = db.get_employee_city_by_id(emp_id)

                assert db_record is not None, f"Employee ID {emp_id} not found in database"

                db_city = db_record["city"]

                assert ui_city == db_city, (
                    f"City mismatch for EmpID {emp_id}. "
                    f"UI City: {ui_city}, DB City: {db_city}"
                )

        finally:
            db.close_connection()