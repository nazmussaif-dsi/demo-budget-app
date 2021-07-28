package com.saif.repository;

import com.saif.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseRepository {
    private static final List<Expense> expenseList = new ArrayList<>();

    public void saveExpense(Expense expense) {
        long id = expenseList.size() + 1;
        expense.setId(id);
        expenseList.add(expense);
    }

    public Expense getExpenseById(Long id) {
        for (Expense expense : expenseList) {
            if (expense.getId() == id) {
                return expense;
            }
        }
        return null;
    }

    public List<Expense> getAllExpenses() {
        return expenseList;
    }

    public void deleteExpense(Long id) { expenseList.removeIf(expense -> expense.getId() == id);}
}
