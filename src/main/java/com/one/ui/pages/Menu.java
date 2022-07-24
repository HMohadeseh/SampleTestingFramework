package com.one.ui.pages;

import com.one.framework.Browser;

import static com.one.locators.ClassName.SHOPPING_CART;

import static com.one.locators.Id.CLOSE_MENU;
import static com.one.locators.Id.LOGOUT;
import static com.one.locators.XPathSelector.CLOSE_MENU_BUTTON;

public class Menu {

    private Browser browser;

    public Menu(Browser browser) {
        this.browser = browser;
    }

    public boolean isCloseMenuVisible() {
        return browser.elementIsDisplayed(CLOSE_MENU);
    }

    public boolean isLogoutVisible() {
        return browser.elementIsDisplayed(LOGOUT);
    }

    public void  closeMenu() {
        browser.click(CLOSE_MENU_BUTTON);
    }

    public void  clickLogout() {
        browser.click(LOGOUT);
    }

    public void clickShoppingCartButton() {
        browser.click(SHOPPING_CART);
    }

}
