package com.example.springbootreactiveecommerce.service;

import com.example.springbootreactiveecommerce.domain.Item;
import com.example.springbootreactiveecommerce.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

@Service
public class InventoryService {

    private final ItemRepository exampleRepository;

    public InventoryService(ItemRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {

        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (
                useAnd
                ? matchingAll()
                : matchingAny()
        )
                .withStringMatcher(CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        Example<Item> prob = Example.of(item, matcher);

        return exampleRepository.findAll(prob);
    }

}
