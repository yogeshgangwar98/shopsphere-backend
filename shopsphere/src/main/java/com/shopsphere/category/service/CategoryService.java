package com.shopsphere.category.service;

import com.shopsphere.category.dto.CategoryCreateRequest;
import com.shopsphere.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryCreateRequest request);
    CategoryResponse getCategoryById(Long id);
    List<CategoryResponse> getAllCategories();
    void deleteCategory(Long id);
}
