package com.one;

import com.one.framework.Browser;
import com.one.framework.WebDriverConfig;
import com.one.ui.pages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = {LoginForm.class, WebDriverConfig.class, Browser.class})
public class ProductsTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginForm loginForm;

    @Inject
    private Browser browser;

    private Menu menu;
    private Cart cart;
    private ProductsContent productsContent;
    private CheckoutInformation information;
    private CheckoutOverview checkoutOverview;
    private CheckoutComplete checkoutComplete;

    @BeforeClass(alwaysRun = true)
    @Parameters({"username", "password"})
    public void beforeTestMethod(String username, String password) {
        loginForm.loginAs(username, password);
        menu = new Menu(browser);
        cart = new Cart(browser);
        productsContent = new ProductsContent(browser);
        information = new CheckoutInformation(browser);
        checkoutOverview = new CheckoutOverview(browser);
        checkoutComplete = new CheckoutComplete(browser);
    }

    @Test(groups = "addProductsToCart")
    @Parameters({"username", "password"})
    public void addProducts() {

        assertEquals(productsContent.getItemText(), "Test.allTheThings() T-Shirt (Red)");

        productsContent.addToCart();
        assertEquals(productsContent.getButtonText(), "REMOVE");
        assertTrue(productsContent.getShoppingCartBadge().equals("1"), "1");

        menu.clickShoppingCartButton();
        assertEquals(productsContent.getItemText(), "Test.allTheThings() T-Shirt (Red)");

        cart.clickCheckoutButton();
        information.enterInformation("mohadeseh", "hamidi", "12345678");

        checkoutOverview.clickFinishButton();
        assertEquals(checkoutComplete.getSuccessfulOrderText(), "THANK YOU FOR YOUR ORDER");
    }


}
