package com.example.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryInput(@NotBlank(message = "Category name is required") String name) {

}
