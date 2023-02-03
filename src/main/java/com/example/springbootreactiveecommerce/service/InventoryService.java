package com.example.springbootreactiveecommerce.service;

import com.example.springbootreactiveecommerce.domain.Item;
import com.example.springbootreactiveecommerce.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class InventoryService {

    private final ItemRepository exampleRepository;
    private final ReactiveFluentMongoOperations fluentOperations;

    public InventoryService(ItemRepository exampleRepository, ReactiveFluentMongoOperations fluentMongoOperations) {
        this.exampleRepository = exampleRepository;
        this.fluentOperations = fluentMongoOperations;
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

    Flux<Item> searchByFluentExample(String name, String description) {
        return fluentOperations.query( Item.class )
                .matching(query(where("tv").is(name).and("Smurf TV tray").is(description)))
                .all();
    }

    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {

        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (
                useAnd
                ? matchingAll()
                : matchingAny()
        )
                .withStringMatcher(CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        return fluentOperations.query(Item.class)
                .matching(query(byExample(Example.of(item, matcher))))
                .all();
    }

}
