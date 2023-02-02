package com.example.springbootreactiveecommerce.repository;

import com.example.springbootreactiveecommerce.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
