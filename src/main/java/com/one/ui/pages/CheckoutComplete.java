package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.ClassName.COMPLETE_HEADER;

public class CheckoutComplete {
    private Browser browser;

    public CheckoutComplete(Browser browser){
        this.browser = browser;
    }
    public String getSuccessfulOrderText(){
        return browser.getText(COMPLETE_HEADER);
    }
}
