package com.example.risk.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private static long id;

    @Column(name = "cat_name")
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;

        return id == Category.getId();
    }

    public static long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void setId(long id) {
        Category.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Product> getBooks() {
        return books;
    }

    public void setBooks(Set<Product> books) {
        this.books = books;
    }
}
