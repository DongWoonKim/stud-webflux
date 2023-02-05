package com.example.springbootreactiveecommerce.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    void itemBasicsShouldWork() {

        Item sampleItem = new Item("item1", "tv tray", 19.99);

        assertEquals(sampleItem.getName(), "item1");
        assertEquals(sampleItem.getDescription(), "tv tray");
        assertEquals(sampleItem.getPrice(), 19.99);

    }

}