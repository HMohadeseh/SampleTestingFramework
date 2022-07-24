package com.one.ui.pages;

import com.one.framework.Browser;
import com.one.ui.domains.Product;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import io.netty.handler.codec.socks.SocksRequestType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.one.locators.ClassName.SHOPPING_CART_BADGE;
import static com.one.locators.ClassName.SORT;
import static com.one.locators.Id.*;
import static com.one.locators.XPathSelector.*;

public class ProductsContent {

    //private static final Logger logger = LoggerFactory.getLogger(ProductsContent.class);

    private Browser browser;

    public ProductsContent(Browser browser) {
        this.browser = browser;
    }

/*    public ProductsTable<Product> getProductsList() {
        return new ProductsTable<>(browser.await(INVENTORY_CONTAINER),
                cells -> new Product(cells.get(0).getText(),
                       // cells.get(1).getText(),
                       // cells.get(2).getText(),
                        cells.get(1).getText())
        );
    }*/

    public Product getProductFromPosition(int row) {
        return getProductsList().get(row);
    }

    public Product getProductByName(String name) {
        return getProductsList().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
    }

    private List<Product> getProductsList() {
        List<Product> rows = new ArrayList<>();
        browser.await(INVENTORY_LIST).findElements(INVENTORY_ITEM).forEach(content -> {
                    String text = content.toString();
                    // split by new line
                    List<String> info = new ArrayList<>();
                    String[] lines = text.split("\\r?\\n");
                    for (String line : lines) {
                        info.add(line);
                    }
                    try {
                        Product newProduct = new Product(info.get(0),
                                info.get(1),
                                info.get(2),
                                info.get(3));
                        rows.add(newProduct);

                    } catch (Exception e) {
                        //logger.error("Product structure is not the expected one");
                    }
                });
        return rows;
    }


    public void sortBy(String criteria){
        browser.selectByVisibleText(SORT, criteria);
    }

    public void addToCart() {
        browser.click(ADD_TO_CART);
    }

    public String getItemText() {
        return browser.getText(ITEM_TITLE);
    }

    public String getButtonText() {
        return browser.getText(REMOVE_BUTTON);
    }

    public String getShoppingCartBadge() {
        return browser.getText(SHOPPING_CART_BADGE);
    }

}
