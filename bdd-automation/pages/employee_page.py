import time
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC

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
    