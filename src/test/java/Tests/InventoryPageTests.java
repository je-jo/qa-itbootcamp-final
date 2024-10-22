package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
    }

    @Test
    public void allItemsAreDisplayedCorrectly() {
        for (int i = 0; i < inventoryPage.itemNames.size(); i++) {
            String displayedName = inventoryPage.itemNames.get(i).getText();
            String displayedDescription = inventoryPage.itemDescriptions.get(i).getText();
            String displayedPriceString = inventoryPage.itemPrices.get(i).getText();
            String displayedPriceNoCurrency = displayedPriceString.substring(displayedPriceString.lastIndexOf("$") + 1);
//            double displayedPriceNum = Double.parseDouble(displayedPriceNoCurrency);
            String displayedFullImgSrc = inventoryPage.itemImages.get(i).getAttribute("src");
            String displayedImgSrc = displayedFullImgSrc.substring(25);
            String expectedName = excelReader.getStringData("ItemsData", i + 1, 1);
            String expectedDescription = excelReader.getStringData("ItemsData", i + 1, 2);
            // int expectedPrice = excelReader.getIntegerData("ItemsData", i + 1, 3);
            String expectedImgSrc = excelReader.getStringData("ItemsData", i + 1, 4);
            // asertacije
            Assert.assertEquals(displayedName, expectedName);
            Assert.assertEquals(displayedDescription, expectedDescription);
            Assert.assertEquals(displayedImgSrc, expectedImgSrc);
            // System.out.println(expectedPrice);
            Assert.assertTrue(isElemDisplayed(inventoryPage.btnsAddToCart.get(i)));
        }
    }

    @Test
    public void userCanSeeDetailedViewOfAnItem() {
        String[] itemPageNames = new String[inventoryPage.itemNames.size()];
        for (int i = 0; i < inventoryPage.itemNames.size(); i++) {
            inventoryPage.clickOnItemName(i);
            itemPageNames[i] = itemPage.getHeaderText();
            itemPage.clickOnBackToInventory();
        }
        for (int i = 0; i < inventoryPage.itemNames.size(); i++) {
            Assert.assertEquals(itemPageNames[i], inventoryPage.itemNames.get(i).getText());
        }

    }

    @Test
    public void userCanSeeAboutPage() {
        loginPage.clickOnHamburgerMenuIfDisplayed();
        sidebarPage.clickOnAboutLink();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    // sortiranje...

}
