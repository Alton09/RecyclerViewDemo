package com.example.johnqualls.recyclerviewdemo;

/**
 * Created by john.qualls on 3/27/2017.
 */

public class Item {
    private String key;
    private String value;

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
