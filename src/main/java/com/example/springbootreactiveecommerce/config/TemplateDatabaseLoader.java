package com.example.springbootreactiveecommerce.config;

import com.example.springbootreactiveecommerce.domain.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
//            mongo.save(new Item("clock", "Alf alarm clock", 19.99));
//            mongo.save(new Item("tv","Smurf TV tray", 24.99));
        };
    }

}
