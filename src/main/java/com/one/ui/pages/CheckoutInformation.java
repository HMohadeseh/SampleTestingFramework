package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.Id.*;

public class CheckoutInformation {
    private Browser browser;

    public CheckoutInformation(Browser browser) {
        this.browser = browser;
    }

    public void addToCart() {
        browser.click(ADD_TO_CART);
    }

    public void enterInformation(String fistName, String lastName, String postalCode) {
        browser.await(FIRST_NAME).sendKeys(fistName);
        browser.await(LAST_NAME).sendKeys(lastName);
        browser.await(POSTAL_CODE).sendKeys(postalCode);
        browser.click(CONTINUE);
    }
}
