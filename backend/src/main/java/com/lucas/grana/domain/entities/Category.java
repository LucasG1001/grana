package com.lucas.grana.domain.entities;

import java.util.List;

import com.lucas.grana.domain.entities.transaction.Transaction;

public class Category extends BaseEntity {
    private String name;
    private String color;
    private String icon;
    private List<Transaction> transactions;
    private User user;
}
