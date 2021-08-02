package com.saif.controller;

import com.saif.helper.dtos.ExpenseDTO;
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
  public List<ExpenseDTO> getAllExpenses() {
    return expenseService.getAllExpenses();
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ExpenseDTO getExpense(@PathVariable("id") Long id) {
    ExpenseDTO expenseDTO = expenseService.getExpenseById(id);
    return expenseDTO;
  }

  @PostMapping("/add")
  @ResponseBody
  public String addExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) {
    expenseService.saveExpense(expenseDTO);
    return "added";
  }

  @PostMapping("/update")
  @ResponseBody
  public String updateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) {
    expenseService.updateExpense(expenseDTO);
    return "updated";
  }

  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public String deleteExpense(@PathVariable("id") Long id) {
    expenseService.deleteExpense(id);
    return "deleted";
  }
}
