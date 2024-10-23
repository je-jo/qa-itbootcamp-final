package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ETest extends BaseTest {

    @Test
    public void endToEndTest() {
        // login
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        // add item to cart
        inventoryPage.itemNames.get(0).click();
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "1");
        // verify item is in cart
        inventoryPage.clickOnCartLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.cartItemNames.get(0), inventoryPage.itemNames.get(0));
        // enter checkout data
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        // finish checkout
        Assert.assertEquals(checkoutOverviewPage.cartItemNames.get(0).getText(), inventoryPage.itemNames.get(0).getText());
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.headerCheckoutComplete.getText(), "Thank you for your order!");
        Assert.assertTrue(isElemDisplayed(checkoutCompletePage.btnBackToProducts));
        // back to all products and verify cart is empty
        checkoutCompletePage.clickOnBackToProductsButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertFalse(isElemDisplayed(inventoryPage.badgeCart));
        // log out
        logOut();
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));

    }
}
