package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void UserCanLogOut(){
        logIn("standard_user", "secret_sauce");
        loginPage.clickOnHamburgerMenuIfDisplayed();
        sidebarPage.clickOnLogoutLink();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }

    @Test
    public void UserRemainsLoggedOutAfterPageRefresh(){
        logIn("standard_user", "secret_sauce");
        loginPage.clickOnHamburgerMenuIfDisplayed();
        sidebarPage.clickOnLogoutLink();
        driver.navigate().refresh();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
    }


}
