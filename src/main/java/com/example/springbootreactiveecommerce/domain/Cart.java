package com.example.springbootreactiveecommerce.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Cart {

    private @Id String id;
    private List<CartItem> cartItems;

    private Cart() {}

    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }
}
