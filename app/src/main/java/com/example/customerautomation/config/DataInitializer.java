package com.example.customerautomation.config;

import com.example.customerautomation.entity.Employee;
import com.example.customerautomation.entity.Transaction;
import com.example.customerautomation.repository.EmployeeRepository;
import com.example.customerautomation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if transaction data already exists to avoid duplicates
        if (transactionRepository.count() == 0) {
            // January transactions
            transactionRepository.save(new Transaction("Office Supplies", 150.00, LocalDate.of(2026, 1, 5), "January"));
            transactionRepository.save(new Transaction("Client Meeting", 75.50, LocalDate.of(2026, 1, 10), "January"));
            transactionRepository.save(new Transaction("Software License", 299.99, LocalDate.of(2026, 1, 15), "January"));

            // February transactions
            transactionRepository.save(new Transaction("Equipment Purchase", 500.00, LocalDate.of(2026, 2, 3), "February"));
            transactionRepository.save(new Transaction("Training Course", 200.00, LocalDate.of(2026, 2, 12), "February"));
            transactionRepository.save(new Transaction("Travel Expenses", 450.75, LocalDate.of(2026, 2, 18), "February"));

            // March transactions
            transactionRepository.save(new Transaction("Marketing Campaign", 1200.00, LocalDate.of(2026, 3, 5), "March"));
            transactionRepository.save(new Transaction("Office Rent", 2000.00, LocalDate.of(2026, 3, 1), "March"));
            transactionRepository.save(new Transaction("Utilities", 350.00, LocalDate.of(2026, 3, 20), "March"));

            // April transactions
            transactionRepository.save(new Transaction("Consulting Services", 800.00, LocalDate.of(2026, 4, 7), "April"));
            transactionRepository.save(new Transaction("Hardware Upgrade", 1500.00, LocalDate.of(2026, 4, 14), "April"));

            System.out.println("Sample transaction data loaded successfully!");
        }

        if (employeeRepository.count() < 21) {
            saveEmployeeIfMissing(1, 50000, "Chennai", "TN");
            saveEmployeeIfMissing(2, 40000, "Bangalore", "KA");
            saveEmployeeIfMissing(3, 90000, "Kolkata", "WB");
            saveEmployeeIfMissing(4, 72000, "Hyderabad", "TS");
            saveEmployeeIfMissing(5, 65000, "Mumbai", "MH");
            saveEmployeeIfMissing(6, 58000, "Pune", "MH");
            saveEmployeeIfMissing(7, 47000, "Ahmedabad", "GJ");
            saveEmployeeIfMissing(8, 53000, "Jaipur", "RJ");
            saveEmployeeIfMissing(9, 46000, "Lucknow", "UP");
            saveEmployeeIfMissing(10, 82000, "Delhi", "DL");
            saveEmployeeIfMissing(11, 49000, "Kochi", "KL");
            saveEmployeeIfMissing(12, 87000, "Surat", "GJ");
            saveEmployeeIfMissing(13, 61000, "Visakhapatnam", "AP");
            saveEmployeeIfMissing(14, 54000, "Indore", "MP");
            saveEmployeeIfMissing(15, 77000, "Nagpur", "MH");
            saveEmployeeIfMissing(16, 68000, "Bhopal", "MP");
            saveEmployeeIfMissing(17, 59000, "Patna", "BR");
            saveEmployeeIfMissing(18, 73000, "Vijayawada", "AP");
            saveEmployeeIfMissing(19, 51000, "Vadodara", "GJ");
            saveEmployeeIfMissing(20, 66000, "Chandigarh", "CH");
            saveEmployeeIfMissing(21, 88000, "Noida", "UP");
            System.out.println("Sample employee data loaded successfully!");
        }
    }

    private void saveEmployeeIfMissing(int empId, int salary, String city, String state) {
        if (!employeeRepository.existsById(empId)) {
            employeeRepository.save(new Employee(empId, salary, city, state));
        }
    }
}
