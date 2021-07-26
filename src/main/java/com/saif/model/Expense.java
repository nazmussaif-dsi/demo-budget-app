package com.saif.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Expense {

    @Id
    private Long id;
    private Date expenseDate;
    private String description;
    private String location;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
