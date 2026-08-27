package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductInput;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Product> search(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Transactional(readOnly = true)
    public List<Product> findByCategory(Long categoryId) {
        categoryService.findById(categoryId);
        return productRepository.findByCategoryId(categoryId);
    }

    public Product create(ProductInput input) {
        Category category = categoryService.findById(input.categoryId());
        return productRepository.save(new Product(input.name(), input.description(), input.price(), input.stock(), category));
    }

    public Product update(Long id, ProductInput input) {
        Product product = findById(id);
        Category category = categoryService.findById(input.categoryId());
        product.setName(input.name());
        product.setDescription(input.description());
        product.setPrice(input.price());
        product.setStock(input.stock());
        product.setCategory(category);
        return productRepository.save(product);
    }

    public boolean delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
        return true;
    }
}
