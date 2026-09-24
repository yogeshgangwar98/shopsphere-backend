package com.shopsphere.category.service;

import com.shopsphere.category.dto.CategoryCreateRequest;
import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.entity.Category;
import com.shopsphere.category.mapper.CategoryMapper;
import com.shopsphere.category.repository.CategoryRepository;
import com.shopsphere.common.exception.DuplicateResourceException;
import com.shopsphere.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request) {
        if (categoryRepository.existsByName(request.name())) {
            throw new DuplicateResourceException(
                    "Category already exists: " + request.name()
            );
        }

        Category category = new Category(request.name());

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);

    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
       Category category = categoryRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Category not found:"+id));
       return categoryMapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("category not found"));
        categoryRepository.delete(category);
    }
}
