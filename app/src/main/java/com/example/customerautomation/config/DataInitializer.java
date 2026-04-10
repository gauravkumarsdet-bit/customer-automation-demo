package com.example.customerautomation.config;

import com.example.customerautomation.entity.Transaction;
import com.example.customerautomation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicates
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

            System.out.println("Sample data loaded successfully!");
        }
    }
}
