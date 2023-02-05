package com.example.springbootreactiveecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.JavaVersion;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringBootReactiveEcommerceApplication {

    public static void main(String[] args) {

        BlockHound.builder()
                .allowBlockingCallsInside(
                        TemplateEngine.class.getCanonicalName(), "process"
                ).install();
        SpringApplication.run(SpringBootReactiveEcommerceApplication.class, args);
    }

}
