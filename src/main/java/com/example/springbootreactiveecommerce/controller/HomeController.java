package com.example.springbootreactiveecommerce.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    Mono<String> home() {
        log.info("home cont");
        return Mono.just("home");
    }

}
