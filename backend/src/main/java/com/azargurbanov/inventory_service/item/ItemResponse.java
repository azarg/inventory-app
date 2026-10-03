package com.azargurbanov.inventory_service.item;

public record ItemResponse(Long id, String sku, String name, int balance) {

    static ItemResponse from(Item item) {
        return new ItemResponse(item.getId(), item.getSku(), item.getName(), item.getBalance());
    }
}