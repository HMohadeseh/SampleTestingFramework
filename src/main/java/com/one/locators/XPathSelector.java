package com.one.locators;

import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.xpath;

public enum XPathSelector implements Supplier<By> {

    // Login page
    lOGIN_BOX("//div[@class='login-box']"),
    LOGIN_ERROR_MESSAGE(lOGIN_BOX.get(),"//button[contains(@class,'error-button')]"),

    // left side menu
    OPEN_MENU_BUTTON("//button[contains(text(),'Open Menu')]"),
    CLOSE_MENU_BUTTON("//button[contains(text(),'Close Menu')]"),

    //inventory section
    INVENTORY_ITEMS_DETAILS("//div[contains(@class,'inventory_item_')]/a"),
    INVENTORY_LIST("//div[@class='inventory_list']"),
    INVENTORY_ITEM("//div[@class='inventory_item']"),
    INVENTORY_ITEM_NAME("//div[@class='inventory_item_name']"),
    ITEM_TITLE("//a[@id='item_3_title_link']//div")

;

    private By by;


    XPathSelector(String id) {
        this.by = xpath(id);
    }

    XPathSelector(By id, String id2) {
        String newid = clearString(id) + id2;
        this.by = xpath(newid);
    }

    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }


    public String clearString(By id) {
        return id.toString().replace("By.xpath: ", "");
    }


}