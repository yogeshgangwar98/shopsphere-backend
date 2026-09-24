package com.shopsphere.product.service;

import com.shopsphere.common.exception.DuplicateResourceException;
import com.shopsphere.common.exception.ResourceNotFoundException;
import com.shopsphere.product.dto.ProductRequest;
import com.shopsphere.product.dto.ProductResponse;
import com.shopsphere.product.entity.Product;
import com.shopsphere.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        if(productRepository.existsBySku(request.getSku())){
            throw new DuplicateResourceException("Product already present");
        }
        Product product = new Product();
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setActive(true);

        Product savedProduct = productRepository.save(product);

        return mapToResponse(savedProduct);

    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> res = new ArrayList<>();
        for(Product pro : products){
            res.add(mapToResponse(pro));
        }
        return res;
//        return productRepository.findAll().stream().map(this::mapToResponse).toList();

    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Product found with given ID: "+ id));
        product.setName(request.getName());
        product.setSku(request.getSku());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
       Product product =  productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with given ID: "+id));
        productRepository.delete(product);
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setSku(product.getSku());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setActive(product.getActive());

        return response;
    }
}
