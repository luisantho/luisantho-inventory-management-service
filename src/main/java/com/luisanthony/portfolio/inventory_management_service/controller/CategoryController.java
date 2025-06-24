package com.luisanthony.portfolio.inventory_management_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisanthony.portfolio.inventory_management_service.model.Category;
import com.luisanthony.portfolio.inventory_management_service.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById (@PathVariable Long id) {
        return new ResponseEntity<>(service.getCategoryById(id),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
        return new ResponseEntity<>(service.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
