package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryInput;
import com.example.ecommerce.dto.ProductInput;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductGraphQLController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Product product(@Argument Long id) {
        return productService.findById(id);
    }

    @QueryMapping
    public List<Product> searchProducts(@Argument String keyword) {
        return productService.search(keyword);
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productService.findByCategory(categoryId);
    }

    @QueryMapping
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Category category(@Argument Long id) {
        return categoryService.findById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument @Valid ProductInput input) {
        return productService.create(input);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument @Valid ProductInput input) {
        return productService.update(id, input);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        return productService.delete(id);
    }

    @MutationMapping
    public Category createCategory(@Argument @Valid CategoryInput input) {
        return categoryService.create(input);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument @Valid CategoryInput input) {
        return categoryService.update(id, input);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        return categoryService.delete(id);
    }
}
