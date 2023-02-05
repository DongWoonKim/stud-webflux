package com.example.springbootreactiveecommerce.sample;

import com.example.springbootreactiveecommerce.dto.Dish;
import com.example.springbootreactiveecommerce.service.KitchenService;
import reactor.core.publisher.Flux;

public class PoliteServer {

    private final KitchenService kitchen;

    public PoliteServer(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    public Flux<Dish> doingMyJob() {

        return this.kitchen.getDishes()
                .doOnNext(dish -> System.out.println("Thank you for " + dish + "!"))
                .doOnError(error -> System.out.println("So sorry about " + error.getMessage()))
                .doOnComplete(() -> System.out.println("Thanks for all your hard work!"))
                .map(Dish::deliver);
    }

}
