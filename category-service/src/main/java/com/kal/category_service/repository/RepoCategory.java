package com.kal.category_service.repository;

import com.kal.category_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoCategory extends JpaRepository<Category, Integer> {
    List<Category> findByStatus(Integer status);
    Optional<Category> findByCategory(String category);
    Optional<Category> findByTag(String tag);
}
