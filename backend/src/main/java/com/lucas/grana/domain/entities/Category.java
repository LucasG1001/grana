package com.lucas.grana.domain.entities;

import java.util.List;

import com.lucas.grana.domain.valueObjects.HexColor;
import com.lucas.grana.domain.valueObjects.Icon;
import com.lucas.grana.domain.valueObjects.category.CategoryName;

import java.util.Objects;

public class Category extends BaseEntity {
    private final CategoryName categoryName;
    private final HexColor color;
    private final Icon icon;
    private final User user;

    public Category(CategoryName categoryName, HexColor color, Icon icon, User user, List<Transaction> transactions) {
        this.categoryName = categoryName;
        this.color = color;
        this.icon = icon;
        this.user = Objects.requireNonNull(user, "User não pode ser nulo");
    }

    public CategoryName getName() {
        return categoryName;
    }

    public HexColor getColor() {
        return color;
    }

    public Icon getIcon() {
        return icon;
    }

    public User getUser() {
        return user;
    }
}
