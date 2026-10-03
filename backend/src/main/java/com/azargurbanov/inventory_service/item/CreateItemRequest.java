package com.azargurbanov.inventory_service.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateItemRequest(
        @NotBlank @Size(max = 50) String sku,
        @NotBlank @Size(max = 200) String name) {
}