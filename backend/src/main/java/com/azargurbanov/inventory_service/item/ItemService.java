package com.azargurbanov.inventory_service.item;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemResponse> findAll() {
        return itemRepository.findAll().stream()
                .map(ItemResponse::from)
                .toList();
    }
    
    @Transactional
    public ItemResponse create(CreateItemRequest request) {
        Item item = itemRepository.save(new Item(request.sku(), request.name()));
        return ItemResponse.from(item);
    }
}