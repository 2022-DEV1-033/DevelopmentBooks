package com.bnpp.katas.developmentbooks.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private BigDecimal price;
}
