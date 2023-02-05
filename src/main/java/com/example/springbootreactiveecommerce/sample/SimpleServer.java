package com.example.springbootreactiveecommerce.sample;

import com.example.springbootreactiveecommerce.dto.Dish;
import com.example.springbootreactiveecommerce.service.KitchenService;
import reactor.core.publisher.Flux;

public class SimpleServer {

    private final KitchenService kitchen;

    public SimpleServer(KitchenService kitchenService) {
        this.kitchen = kitchenService;
    }

    Flux<Dish> doingMyJob() {
        return this.kitchen.getDishes()
                .map(dish -> Dish.deliver(dish));
    }

}
