package com.example.risk.service;
import com.example.risk.model.Category;

import java.util.List;


public interface  CategoryService {

    void save(Category category);
    List<Category> findAll();
}
