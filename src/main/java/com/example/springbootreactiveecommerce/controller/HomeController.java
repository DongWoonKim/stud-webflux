package com.example.springbootreactiveecommerce.controller;

import com.example.springbootreactiveecommerce.domain.Cart;
import com.example.springbootreactiveecommerce.domain.CartItem;
import com.example.springbootreactiveecommerce.domain.Item;
import com.example.springbootreactiveecommerce.repository.CartRepository;
import com.example.springbootreactiveecommerce.repository.ItemRepository;
import com.example.springbootreactiveecommerce.service.CartService;
import com.example.springbootreactiveecommerce.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.*;

@Slf4j
@Controller
public class HomeController {

    private InventoryService inventoryService;

    public HomeController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    // end::1[]

    // tag::2[]
    @GetMapping("/")
    Mono<Rendering> home() { // <1>
        return Mono.just(Rendering.view("home.html") // <2>
                .modelAttribute("items", this.inventoryService.getInventory()) // <3>
                .modelAttribute("cart", this.inventoryService.getCart("My Cart") // <4>
                        .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
    // end::2[]

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.inventoryService.addItemToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @DeleteMapping("/remove/{id}")
    Mono<String> removeFromCart(@PathVariable String id) {
        return this.inventoryService.removeOneFromCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @PostMapping
    Mono<String> createItem(@ModelAttribute Item newItem) {
        return this.inventoryService.saveItem(newItem) //
                .thenReturn("redirect:/");
    }

    @PostMapping("/delete/{id}")
    Mono<String> deleteItem(@PathVariable String id) {
        System.out.println("hihihihi");
        return this.inventoryService.deleteItem(id)
                .thenReturn("redirect:/");
    }

}
