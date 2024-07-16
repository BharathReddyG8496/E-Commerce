package com.bharath.ecommerce.ecommerce.entities.DTOs;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
public class ProductDTO {
    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Short description is required")
    private String shortDescription;

    @NotBlank(message = "Long description is required")
    private String longDescription;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price must be a positive value")
    private Double price;

    @NotNull(message = "Inventory count is required")
    @Min(value = 0, message = "Inventory count must be a positive value")
    private Integer inventoryCount;

    @NotBlank(message = "Category name is required")
    private String categoryName;
}

