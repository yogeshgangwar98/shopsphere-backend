package com.shopsphere.category.mapper;

import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category){
        return new CategoryResponse(category.getId(), category.getName());
    }
}
