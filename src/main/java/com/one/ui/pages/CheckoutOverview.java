package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.Id.FINISH;


public class CheckoutOverview {
    private Browser browser;

    public CheckoutOverview(Browser browser) {
        this.browser = browser;
    }

    public void clickFinishButton() {
        browser.click(FINISH);
    }
}
