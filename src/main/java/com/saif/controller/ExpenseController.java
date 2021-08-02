package com.saif.controller;

import com.saif.model.Expense;
import com.saif.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/expenses")
public class ExpenseController {
  private final ExpenseService expenseService;

  @GetMapping
  public String showAllExpenses() {
    return "expenses/deaily_expenses";
  }

  @GetMapping("/all")
  @ResponseBody
  public List<Expense> getAllExpenses() {
    System.out.println("SUS");
    List<Expense> tmp = expenseService.getAllExpenses();
    System.out.println(tmp);
    return tmp;
  }

  @GetMapping("/{id}")
  @ResponseBody
  public Expense getExpense(@PathVariable("id") Long id) {
    Expense expense = expenseService.getExpenseById(id);
    return expense;
  }

  @PostMapping("/add")
  @ResponseBody
  public String addExpense(@ModelAttribute("expense") Expense expense) {
    expenseService.saveExpense(expense);
    return "added";
  }

  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public String deleteExpense(@PathVariable("id") Long id) {
    expenseService.deleteExpense(id);
    return "deleted";
  }
}
