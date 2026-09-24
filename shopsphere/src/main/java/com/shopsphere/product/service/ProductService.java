package com.shopsphere.product.service;

import com.shopsphere.product.dto.ProductRequest;
import com.shopsphere.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(Long id, ProductRequest request);
    void deleteProduct(Long id);
}
