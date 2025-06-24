package com.luisanthony.portfolio.inventory_management_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisanthony.portfolio.inventory_management_service.exception.ResourceNotFoundException;
import com.luisanthony.portfolio.inventory_management_service.model.Category;
import com.luisanthony.portfolio.inventory_management_service.repository.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category getCategoryById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found by id " + id));
    }

    @Transactional
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Transactional
    public void deleteCategory (Long id) {
        if (!repository.existsById(id)) { //SELECT COUNT(*)
            throw new ResourceNotFoundException("Category not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
