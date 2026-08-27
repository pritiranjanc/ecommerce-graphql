package com.example.ecommerce.service;

import com.example.ecommerce.dto.CategoryInput;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Category create(CategoryInput input) {
        repository.findByNameIgnoreCase(input.name()).ifPresent(c -> {
            throw new IllegalArgumentException("Category already exists: " + input.name());
        });
        return repository.save(new Category(input.name()));
    }

    public Category update(Long id, CategoryInput input) {
        Category category = findById(id);
        category.setName(input.name());
        return repository.save(category);
    }

    public boolean delete(Long id) {
        Category category = findById(id);
        repository.delete(category);
        return true;
    }
}
