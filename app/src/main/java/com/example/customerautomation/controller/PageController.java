package com.example.customerautomation.controller;

import com.example.customerautomation.entity.Transaction;
import com.example.customerautomation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private TransactionService transactionService;

    private boolean isLoggedIn(HttpSession session) {
        Boolean loggedIn = (Boolean) session.getAttribute("isLoggedIn");
        return loggedIn != null && loggedIn;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/transactions")
    public String showTransactions(
            @RequestParam(required = false) String month,
            HttpSession session,
            Model model) {

        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }

        List<Transaction> transactions;
        Double total = 0.0;

        if (month != null && !month.isEmpty()) {
            transactions = transactionService.getTransactionsByMonth(month);
            total = transactionService.calculateTotal(transactions);
            model.addAttribute("selectedMonth", month);
        } else {
            transactions = List.of();
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("total", total);
        model.addAttribute("months", java.util.Arrays.asList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));

        return "transactions";
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        return "redirect:/home";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "profile";
    }

    @GetMapping("/employees")
    public String showEmployees(HttpSession session, Model model) {
        if (!isLoggedIn(session)) {
            return "redirect:/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "employees";
    }
}
