package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
    }

    @Test
    public void userCanAddItemToCartFromInventoryPage() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "1");
        Assert.assertEquals(cartPage.cartItemNames.get(0), inventoryPage.itemNames.get(0));
    }

    @Test
    public void userCanAddItemToCartFromItemPage() {
        inventoryPage.clickOnItemName(0);
        wait.until(ExpectedConditions.visibilityOf(itemPage.btnAddToCart));
        itemPage.clickOnAddToCartButton();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "1");
        Assert.assertEquals(cartPage.cartItemNames.get(0), inventoryPage.itemNames.get(0));
    }

    @Test
    public void userCanAddMultipleItemsToCartFromInventoryPage() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.btnsAddToCart.get(1).click();
        inventoryPage.btnsAddToCart.get(2).click();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "3");
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(cartPage.cartItemNames.get(i), inventoryPage.itemNames.get(i));
        }
    }

    @Test
    public void userCanRemoveItemFromCart() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.removeItemFromCart(0);
        // asertacije
        Assert.assertFalse(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(cartPage.cartItemNames.size(), 0);
    }

    @Test
    public void userCanRemoveItemFromCartFromInventoryPage() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.btnsRemoveFromCart.get(0).click();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertFalse(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(cartPage.cartItemNames.size(), 0);
    }

    @Test
    public void CartStateIsPreservedAfterLogOut() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.btnsAddToCart.get(1).click();
        logOut();
        driver.navigate().refresh();
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "2");
        Assert.assertEquals(cartPage.cartItemNames.get(0), inventoryPage.itemNames.get(0));

    }


    @AfterMethod
    public void logOutAndClearCartAfterEachTest() throws InterruptedException {
        Thread.sleep(3000);
        cartPage.removeAllFromCart();
        clearCookies();
    }
}
