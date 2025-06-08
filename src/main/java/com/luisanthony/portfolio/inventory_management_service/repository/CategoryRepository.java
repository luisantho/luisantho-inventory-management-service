package com.luisanthony.portfolio.inventory_management_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luisanthony.portfolio.inventory_management_service.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
