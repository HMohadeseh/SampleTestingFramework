package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.Id.CHECKOUT;


public class Cart {
    private Browser browser;

    public Cart(Browser browser) {
        this.browser = browser;
    }

    public void clickCheckoutButton() {
        browser.click(CHECKOUT);
    }
}
