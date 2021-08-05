package com.saif.helper.dtos;

import com.saif.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {

  private Long id;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date expenseDate;

  @NonNull
  private Double amount;

  @Size(max = 1000)
  private String description;

  @NonNull
  private Long userId;

  @NonNull
  private Category category;
}
