package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
        logOut();
    }

    @Test(priority = 10)
    public void UserCanLogOut(){
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }

    @Test(priority = 20)
    public void UserRemainsLoggedOutAfterPageRefresh(){
        driver.navigate().refresh();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }

    @Test(priority = 30)
    public void UserCannotReachInventoryPageLoggedOut() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertTrue(isElemDisplayed(loginPage.msgError));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/inventory.html' when you are logged in.");
    }

    @Test(priority = 40)
    public void UserCannotReachCartPageLoggedOut() {
        driver.navigate().to("https://www.saucedemo.com/cart.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertTrue(isElemDisplayed(loginPage.msgError));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/cart.html' when you are logged in.");
    }

    @Test(priority = 50)
    public void UserCannotReachCheckoutFormPageLoggedOut() {
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
        Assert.assertTrue(isElemDisplayed(loginPage.msgError));
        Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: You can only access '/checkout-step-one.html' when you are logged in.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
