package Tests;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
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

    @Test(priority = 10)
    public void userCanAddItemToCartFromInventoryPage() {
        int randomIndex = getRandomIndex(inventoryPage.itemNames.size());
        String addedItemName = inventoryPage.itemNames.get(randomIndex).getText();
        inventoryPage.btnsAddToCart.get(randomIndex).click();
        String newButtonText = inventoryPage.btnsAll.get(randomIndex).getText();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertEquals(newButtonText, "Remove");
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "1");
        Assert.assertEquals(cartPage.cartItemNames.get(0).getText(), addedItemName);
    }

    @Test(priority = 20)
    public void userCanAddItemToCartFromItemPage() {
        int randomIndex = getRandomIndex(inventoryPage.itemNames.size());
        String addedItemName = inventoryPage.itemNames.get(randomIndex).getText();
        inventoryPage.clickOnItemName(randomIndex);
        itemPage.clickOnAddToCartButton();
        String newButtonText = itemPage.btnGeneral.getText();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertEquals(newButtonText, "Remove");
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), "1");
        Assert.assertEquals(cartPage.cartItemNames.get(0).getText(), addedItemName);
    }

    @Test(priority = 30)
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

    @Test(priority = 40)
    public void userCanRemoveItemFromCart() {
        int randomIndex = getRandomIndex(inventoryPage.itemNames.size());
        inventoryPage.btnsAddToCart.get(randomIndex).click();
        inventoryPage.clickOnCartLink();
        cartPage.removeItemFromCart(0);
        // asertacije
        Assert.assertFalse(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(cartPage.cartItemNames.size(), 0);
    }

    @Test(priority = 50)
    public void userCanRemoveItemFromCartFromInventoryPage() {
        int randomIndex = getRandomIndex(inventoryPage.itemNames.size());
        inventoryPage.btnsAddToCart.get(randomIndex).click();
        inventoryPage.btnsAll.get(randomIndex).click();
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertFalse(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(cartPage.cartItemNames.size(), 0);
    }

    @Test(priority = 60)
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
        Assert.assertEquals(cartPage.cartItemNames.get(1), inventoryPage.itemNames.get(1));
    }

    @Test(priority = 70)
    public void userCanAddAllItemsToCart() {
        int numOfItemsToAdd = inventoryPage.btnsAddToCart.size();
        for (int i = numOfItemsToAdd - 1; i >= 0; i--) {
            inventoryPage.clickOnItemAddToCartButton(i);
        }
        inventoryPage.clickOnCartLink();
        // asertacije
        Assert.assertTrue(isElemDisplayed(inventoryPage.badgeCart));
        Assert.assertEquals(inventoryPage.badgeCart.getText(), String.valueOf(numOfItemsToAdd));
        for (int i = 0; i < cartPage.cartItemNames.size(); i++) {
            Assert.assertEquals(cartPage.cartItemNames.get(i).getText(), inventoryPage.itemNames.get(i).getText());
        }
    }


    @AfterMethod
    public void logOutAndClearCartAfterEachTest() throws InterruptedException {
        Thread.sleep(1500);
        cartPage.removeAllFromCart();
        clearCookies();
    }

    // helpers

    public int getRandomIndex(int max) {
        return (int) ((Math.random() * max));
    }

/*    public int[] getArrayOfRandomIndexes(int arrayLength, int max) {
        int[] randomArray = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = getRandomIndex(max);
            System.out.println(randomArray[i]);
        }
    }*/
}
