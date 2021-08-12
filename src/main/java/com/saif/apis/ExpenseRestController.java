package com.saif.apis;

import com.saif.helper.dtos.ExpenseDTO;
import com.saif.helper.exception.ServiceException;
import com.saif.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/expenses")
public class ExpenseRestController {
  private final ExpenseService expenseService;

  @GetMapping("user/{user_id}")
  public List<ExpenseDTO> findAllByUserId(@PathVariable("user_id") Long userId) {
    return expenseService.findAllByUserId(userId);
  }

  @GetMapping("/{id}")
  public ExpenseDTO findById(@PathVariable("id") Long id) throws ServiceException {
    return expenseService.findById(id);
  }

  @PostMapping("/add")
  @ResponseStatus(HttpStatus.CREATED)
  public ExpenseDTO create(@RequestBody ExpenseDTO expenseDTO) throws ServiceException{
    return expenseService.create(expenseDTO);
  }

  @PostMapping("/update")
  public ExpenseDTO update(@RequestBody ExpenseDTO expenseDTO) throws ServiceException{
    return expenseService.update(expenseDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long id) {
    expenseService.delete(id);
  }
}
