package com.example.springbootreactiveecommerce.repository;

import com.example.springbootreactiveecommerce.domain.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
