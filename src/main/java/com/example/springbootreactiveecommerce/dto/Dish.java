package com.example.springbootreactiveecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dish {

    private String description;
    private boolean delivered = false;

    public Dish(String description) {
        this.description = description;
    }

    public static Dish deliver(Dish dish) {
        Dish deliveredDish = new Dish(dish.description);
        deliveredDish.delivered = true;
        return deliveredDish;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "descriptioin='" + description + '\'' +
                ", delivered=" + delivered +
                "}";
    }
}
