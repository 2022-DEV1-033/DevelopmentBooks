package com.bnpp.katas.developmentbooks.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Book {
    @Id
    private Long id;
    private String title;
    private BigDecimal price;
}
