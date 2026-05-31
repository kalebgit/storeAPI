package com.kal.category_service.service;

import com.kal.category_service.dto.DtoCategoryIn;
import com.kal.category_service.entity.Category;

import java.util.List;

public interface SvcCategory {
    List<Category> findAll();
    List<Category> findActive();
    void create(DtoCategoryIn in);
    void update(DtoCategoryIn in, Integer id);
    void enable(Integer id);
    void disable(Integer id);
}
