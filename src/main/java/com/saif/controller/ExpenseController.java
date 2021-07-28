package com.saif.controller;

import com.saif.model.Expense;
import com.saif.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping
    public String showAllExpenses(Model model){
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "expenses/expense_list";
    }

    @GetMapping("/add")
    public String showExpenseForm(Model model){
        model.addAttribute("expense", new Expense());
        return "expenses/expense_form";
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute("expense") Expense expense){
        expenseService.saveExpense(expense);
        return "redirect:/expenses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") Long id){
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }
}
