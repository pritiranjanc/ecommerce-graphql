package com.example.ecommerce.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductInput(
    @NotBlank(message = "Product name is required")
    String name,
    String description,
    @NotNull
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    BigDecimal price,
    @NotNull
    @Min(value = 0, message = "Stock cannot be negative")
    Integer stock,
    @NotNull 
    Long categoryId
) {}
