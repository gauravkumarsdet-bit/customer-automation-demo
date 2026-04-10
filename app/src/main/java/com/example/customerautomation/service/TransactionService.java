package com.example.customerautomation.service;

import com.example.customerautomation.entity.Transaction;
import com.example.customerautomation.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getDescription() == null || transaction.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (transaction.getAmount() == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByMonth(String month) {
        return transactionRepository.findByMonth(month);
    }

    public Double calculateTotal(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
