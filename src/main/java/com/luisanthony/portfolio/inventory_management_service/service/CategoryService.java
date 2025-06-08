package com.luisanthony.portfolio.inventory_management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisanthony.portfolio.inventory_management_service.model.Category;
import com.luisanthony.portfolio.inventory_management_service.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category createCategory(Category category) {
        return repository.save(category);
    }
    
}
