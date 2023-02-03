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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.*;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.*;

@Slf4j
@Controller
public class HomeController {

    private final CartService cartService;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final InventoryService inventoryService;


    public HomeController(
        CartService cartService, ItemRepository itemRepository,
        CartRepository cartRepository, InventoryService inventoryService
    ) {
        this.cartService = cartService;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/")
    Mono<Rendering> home() {
        log.info("home cont");
        return Mono.just(Rendering.view("home.html") // <2>
                .modelAttribute("items", //
                        this.itemRepository.findAll().doOnNext(System.out::println)) // <3>
                .modelAttribute("cart", //
                        this.cartRepository.findById("My Cart") // <4>
                                .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

    @GetMapping("/search")
    Mono<Rendering> search (
        @RequestParam(required = false) String name
        , @RequestParam(required = false) String description
        , @RequestParam boolean useAnd
    ) {
        log.info("search cont");

        return Mono.just(
                Rendering.view("home.html")
                        .modelAttribute("items", this.itemRepository.findAll())
                        .modelAttribute("cart",  this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                        .modelAttribute("results", this.inventoryService.searchByExample(name, description, useAnd))
                        .build()
        );
    }

    @GetMapping("/search/v2")
    Mono<Rendering> searchV2 (
        @RequestParam(required = false) String name
        , @RequestParam(required = false) String description
        , @RequestParam boolean useAnd
    ) {
        log.info("search v2 cont");
        return Mono.just(
                Rendering.view("home.html")
                        .modelAttribute("items", this.itemRepository.findAll())
                        .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                        .modelAttribute("results", this.inventoryService.searchByFluentExample(name, description, useAnd))
                        .build()
        );
    }

}
