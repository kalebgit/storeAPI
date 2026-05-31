package com.kal.category_service.service;

import com.kal.category_service.dto.DtoCategoryIn;
import com.kal.category_service.entity.Category;
import com.kal.category_service.exception.ApiException;
import com.kal.category_service.exception.DBAccessException;
import com.kal.category_service.repository.RepoCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SvcCategoryImp implements SvcCategory {

    private final RepoCategory repoCategory;

    @Override
    public List<Category> findAll() {
        return repoCategory.findAll();
    }

    @Override
    public List<Category> findActive() {
        return repoCategory.findByStatus(1);
    }

    @Override
    public void create(DtoCategoryIn in) {
        Category category = new Category();
        category.setCategory(in.getCategory());
        category.setTag(in.getTag());
        category.setStatus(1);
        try {
            repoCategory.save(category);
        } catch (DataAccessException e) {
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");
            if (msg.contains("ux_category_category")) throw new ApiException("El nombre de categoría ya está registrado", HttpStatus.CONFLICT);
            if (msg.contains("ux_category_tag")) throw new ApiException("El tag ya está registrado", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
    }

    @Override
    public void update(DtoCategoryIn in, Integer id) {
        Category category = repoCategory.findById(id)
                .orElseThrow(() -> new ApiException("Categoría no encontrada con id: " + id, HttpStatus.NOT_FOUND));
        category.setCategory(in.getCategory());
        category.setTag(in.getTag());
        try {
            repoCategory.save(category);
        } catch (DataAccessException e) {
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");
            if (msg.contains("ux_category_category")) throw new ApiException("El nombre de categoría ya está registrado", HttpStatus.CONFLICT);
            if (msg.contains("ux_category_tag")) throw new ApiException("El tag ya está registrado", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
    }

    @Override
    public void enable(Integer id) {
        Category category = repoCategory.findById(id)
                .orElseThrow(() -> new ApiException("Categoría no encontrada con id: " + id, HttpStatus.NOT_FOUND));
        category.setStatus(1);
        repoCategory.save(category);
    }

    @Override
    public void disable(Integer id) {
        Category category = repoCategory.findById(id)
                .orElseThrow(() -> new ApiException("Categoría no encontrada con id: " + id, HttpStatus.NOT_FOUND));
        category.setStatus(0);
        repoCategory.save(category);
    }
}
