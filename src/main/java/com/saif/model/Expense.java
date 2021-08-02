package com.saif.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Expense {

    @Id
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expenseDate;

    private String description;
    private Double amount;

//    @ManyToOne
//    private Category category;
//
//    @ManyToOne
//    private User user;
}
