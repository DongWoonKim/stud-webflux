package com.example.springbootreactiveecommerce.controller;

import com.example.springbootreactiveecommerce.Dish;
import com.example.springbootreactiveecommerce.service.KitchenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(value = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> serveDishes() {
        log.info("in server dish cont");
        return this.kitchenService.getDishes();
    }

    @GetMapping(value = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Dish> deliverDishes() {
        log.info("in served dish cont");
        return this.kitchenService.getDishes()
                .map(dish -> Dish.deliver(dish));
    }
}
