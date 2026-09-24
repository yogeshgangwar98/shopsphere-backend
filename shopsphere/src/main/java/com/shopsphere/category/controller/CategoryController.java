package com.shopsphere.category.controller;

import com.shopsphere.category.dto.CategoryCreateRequest;
import com.shopsphere.category.dto.CategoryResponse;
import com.shopsphere.category.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody CategoryCreateRequest request){
        return this.categoryService.createCategory(request);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable(value = "id") Long id){
        return this.categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return this.categoryService.getAllCategories();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){
        this.categoryService.deleteCategory(id);
    }

}
