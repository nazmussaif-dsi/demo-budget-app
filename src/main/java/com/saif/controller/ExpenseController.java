package com.saif.controller;

import com.saif.helper.dtos.ExpenseDTO;
import com.saif.helper.exception.ServiceException;
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

  @GetMapping("/all/{user_id}")
  @ResponseBody
  public List<ExpenseDTO> getAllExpenses(@PathVariable("user_id") Long id) {
    return expenseService.findAllByUserId(id);
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ExpenseDTO getExpense(@PathVariable("id") Long id) throws ServiceException {
    return expenseService.findById(id);
  }

  @PostMapping("/add")
  @ResponseBody
  public String addExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ServiceException{
    expenseService.create(expenseDTO);
    return "added";
  }

  @PostMapping("/update")
  @ResponseBody
  public String updateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ServiceException{
    System.out.println(expenseDTO);
    expenseService.update(expenseDTO);
    return "updated";
  }

  @DeleteMapping("/delete/{id}")
  @ResponseBody
  public String deleteExpense(@PathVariable("id") Long id) {
    expenseService.delete(id);
    return "deleted";
  }
}
