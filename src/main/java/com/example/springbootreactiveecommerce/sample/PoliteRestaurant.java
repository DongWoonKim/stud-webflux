package com.example.springbootreactiveecommerce.sample;

import com.example.springbootreactiveecommerce.service.KitchenService;

public class PoliteRestaurant {
    public static void main(String[] args) {

        PoliteServer server = new PoliteServer(new KitchenService());

//        server.doingMyJob();

        server.doingMyJob()
                .subscribe(
                        dish -> System.out.println("Consuming " + dish),
                        throwable -> System.out.println(throwable)
                );

    }
}
