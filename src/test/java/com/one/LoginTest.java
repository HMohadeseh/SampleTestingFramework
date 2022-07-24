package com.one;

import com.one.framework.Browser;
import com.one.framework.WebDriverConfig;
import com.one.ui.pages.Header;
import com.one.ui.pages.Inventory;
import com.one.ui.pages.LoginForm;
import com.one.ui.pages.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import javax.inject.Inject;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Reporter;

@ContextConfiguration(classes = {LoginForm.class, WebDriverConfig.class, Browser.class})
public class LoginTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private LoginForm loginForm;

    @Inject
    private Browser browser;


    private Inventory inventory;
    private Header header;
    private Menu menu;
    private String errmessage = "";

    @Test(groups = "page_elements")
    public void verifyLoginPageElements(){
        Reporter.log("Verify elements on login page");
        loginForm.openPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginForm.getPlaceholderTextUsername().matches("Username"), "Username");
        softAssert.assertTrue(loginForm.getPlaceholderTextPassword().matches("Password"), "Password");
        softAssert.assertTrue(loginForm.getLoginButtonText().matches("Login"), "Login");
        softAssert.assertTrue(loginForm.isLogoDisplayed(), "login_logo");
    }

    @Test(groups = "valid")
    public void verifyLogin() {
        inventory = new Inventory(browser);
        assertTrue(inventory.isProductLabelPresent(), "Product label not displayed");
        assertTrue(inventory.getProductLabel().matches("PRODUCTS"));
    }

    @Test(groups = "logout")
    public void verifyLoginLogout() {
        header =  new Header(browser);
        menu = new Menu(browser);
        header.openMenu();
        assertTrue(menu.isCloseMenuVisible(),"Close Menu");
        assertTrue(menu.isLogoutVisible(), "Logout");
        Reporter.log("Verify logout");
        menu.clickLogout();
        assertTrue(loginForm.isLoginDisplayed(), "Login button not found after logout");
    }


    @Test(groups = { "lockedout", "invalid" })
    public void verifyLoginFails() {

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginForm.isLoginErrorMessagePresent(), "Login error button not found");
        String displayedErrMessage = loginForm.getLoginErrorMessage();
        softAssert.assertTrue(displayedErrMessage.contains("Epic sadface: "), "<<Epic sadface>> text not displayed");
        softAssert.assertTrue(displayedErrMessage.contains(errmessage), "The actual message is: " + displayedErrMessage + ", Expected: " + errmessage);
        softAssert.assertAll();

    }

    @BeforeMethod(alwaysRun = true)
    @BeforeGroups(groups = { "valid", "lockedout", "invalid" })
    @Parameters({"username", "password", "message"})
    public void beforeTestMethod(String username, String password, @Optional String message) {
        Reporter.log("Verify login for username <<" + username + ">> and password <<" + password + ">>");
        loginForm.loginAs(username, password);
        errmessage = message;
    }

}
