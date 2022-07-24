package com.one.locators;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.id;

public enum Id implements Supplier<By> {

    LOGIN("login-button"),
    USERNAME("user-name"),
    PASSWORD("password"),
    INVENTORY_CONTAINER("inventory_container"),
    ADD_TO_CART("add-to-cart-test.allthethings()-t-shirt-(red)"),
    REMOVE_BUTTON("remove-test.allthethings()-t-shirt-(red)"),
    CHECKOUT("checkout"),
    FIRST_NAME("first-name"),
    LAST_NAME("last-name"),
    POSTAL_CODE("postal-code"),
    CONTINUE("continue"),
    FINISH("finish"),
    LOGOUT("logout_sidebar_link"),
    CLOSE_MENU("react-burger-cross-btn");


    private final By by;

    Id(String id) {
        this.by = id(id);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}