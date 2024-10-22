package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
    }

    @Test
    public void userCanPurchaseItem() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        Assert.assertEquals(checkoutOverviewPage.cartItemNames.get(0).getText(), inventoryPage.itemNames.get(0).getText());
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.headerCheckoutComplete.getText(), "Thank you for your order!");
        Assert.assertTrue(isElemDisplayed(checkoutCompletePage.btnBackToProducts));
    }

    @Test
    public void userCanPurchaseMultipleItems() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.btnsAddToCart.get(1).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        Assert.assertEquals(checkoutOverviewPage.cartItemNames.get(0).getText(), inventoryPage.itemNames.get(0).getText());
        Assert.assertEquals(checkoutOverviewPage.cartItemNames.get(1).getText(), inventoryPage.itemNames.get(1).getText());
        checkoutOverviewPage.clickOnFinishButton();
        Assert.assertEquals(checkoutCompletePage.headerCheckoutComplete.getText(), "Thank you for your order!");
        Assert.assertTrue(isElemDisplayed(checkoutCompletePage.btnBackToProducts));
    }

    @Test
    public void userCannotPurchaseItemWithoutEnteringFirstName() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutInfoPage.msgError.getText(), "Error: First Name is required");
    }

    @Test
    public void userCannotPurchaseItemWithoutEnteringLastName() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutInfoPage.msgError.getText(), "Error: Last Name is required");
    }

    @Test
    public void userCannotPurchaseItemWithoutEnteringZipCode() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutInfoPage.msgError.getText(), "Error: Postal Code is required");
    }

    @Test
    public void userCannotCheckoutWithEmptyCart() {
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        // and assert error message which doesn't exist
    }

    @Test
    public void userCanCancelCheckout() {
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.clickCancelButton();
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();
        checkoutOverviewPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }


}
