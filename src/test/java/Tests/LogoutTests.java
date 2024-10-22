package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
    }

    @Test
    public void UserCanLogOut(){
        logOut();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }

    @Test
    public void UserRemainsLoggedOutAfterPageRefresh(){
        logOut();
        driver.navigate().refresh();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }

    @Test
    public void UserCannotReachInventoryPageLoggedOut() {
        logOut();
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/inventory.html' when you are logged in.");
    }

    @Test
    public void UserCannotReachCartPageLoggedOut() {
        logOut();
        driver.navigate().to("https://www.saucedemo.com/cart.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/cart.html' when you are logged in.");
    }

    @Test
    public void UserCannotReachCheckoutFormPageLoggedOut() {
        logOut();
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/checkout-step-one.html' when you are logged in.");
    }


}
