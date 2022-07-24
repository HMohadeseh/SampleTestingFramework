package com.one.ui.pages;

import com.one.framework.Browser;
import static com.one.locators.XPathSelector.*;

public class Header {

    private Browser browser;

    public Header(Browser browser) {
        this.browser = browser;
    }

    public void  openMenu() {
        browser.click(OPEN_MENU_BUTTON);
    }

}
