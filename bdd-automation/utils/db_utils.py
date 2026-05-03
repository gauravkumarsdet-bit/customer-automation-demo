import mysql.connector
from utils.config import DB_CONFIG


class DBUtils:

    def __init__(self):
        self.connection = mysql.connector.connect(**DB_CONFIG)
        self.cursor = self.connection.cursor(dictionary=True)

    def get_employee_by_id(self, employee_id):
        query = "SELECT * FROM employees WHERE emp_id = %s"
        self.cursor.execute(query, (employee_id,))
        return self.cursor.fetchone()

    def close_connection(self):
        if self.cursor:
            self.cursor.close()

        if self.connection:
            self.connection.close()
    
    def get_employee_city_by_id(self, employee_id):
        query = "SELECT city FROM employees WHERE emp_id = %s"
        self.cursor.execute(query, (employee_id,))
        return self.cursor.fetchone()