package com.kal.product_service.repository;


import com.kal.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoProduct extends JpaRepository<Product, Integer> {
}
