package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
    }

    @Test(priority = 10)
    public void allItemsAreDisplayedCorrectly() {
        // get data from inventory page
        int totalDisplayedItemCount = inventoryPage.itemNames.size();
        String[] displayedItemNames = new String[totalDisplayedItemCount];
        String[] displayedItemDescriptions = new String[totalDisplayedItemCount];
        double[] displayedItemPrices = new double[totalDisplayedItemCount];
        String[] displayedItemImgSrcs = new String[totalDisplayedItemCount];
        for (int i = 0; i < totalDisplayedItemCount; i++) {
            displayedItemNames[i] = inventoryPage.itemNames.get(i).getText();
            displayedItemDescriptions[i] = inventoryPage.itemDescriptions.get(i).getText();
            String displayedPriceString = inventoryPage.itemPrices.get(i).getText();
            String displayedPriceNoCurrency = displayedPriceString.substring(displayedPriceString.lastIndexOf("$") + 1);
            displayedItemPrices[i] = Double.parseDouble(displayedPriceNoCurrency);
            displayedItemImgSrcs[i] = inventoryPage.itemImages.get(i).getAttribute("src");
        }
        // asertacije
        for (int i = 0; i < inventoryPage.itemNames.size(); i++) {
            Assert.assertEquals(displayedItemNames[i], dataItemNames[i]);
            Assert.assertEquals(displayedItemDescriptions[i], dataItemDescriptions[i]);
            Assert.assertEquals(displayedItemPrices[i], dataItemPrices[i]);
            Assert.assertEquals(displayedItemImgSrcs[i], dataItemImgSrcs[i]);
            Assert.assertTrue(isElemDisplayed(inventoryPage.btnsAddToCart.get(i)));
        }
    }


    @Test(priority = 20)
    public void userCanSeeDetailedViewOfEveryItem() {
        // get data from inventory page
        int totalDisplayedItemCount = inventoryPage.itemNames.size();
        String[] displayedItemNames = new String[totalDisplayedItemCount];
        String[] displayedItemDescriptions = new String[totalDisplayedItemCount];
        double[] displayedItemPrices = new double[totalDisplayedItemCount];
        String[] displayedItemImgSrcs = new String[totalDisplayedItemCount];
        for (int i = 0; i < totalDisplayedItemCount; i++) {
            displayedItemNames[i] = inventoryPage.itemNames.get(i).getText();
            displayedItemDescriptions[i] = inventoryPage.itemDescriptions.get(i).getText();
            String displayedPriceString = inventoryPage.itemPrices.get(i).getText();
            String displayedPriceNoCurrency = displayedPriceString.substring(displayedPriceString.lastIndexOf("$") + 1);
            displayedItemPrices[i] = Double.parseDouble(displayedPriceNoCurrency);
            displayedItemImgSrcs[i] = inventoryPage.itemImages.get(i).getAttribute("src");
        }
        // get data from detail page
        String[] detailItemNames = new String[totalDisplayedItemCount];
        String[] detailItemDescriptions = new String[totalDisplayedItemCount];
        double[] detailItemPrices = new double[totalDisplayedItemCount];
        String[] detailItemImgSrcs = new String[totalDisplayedItemCount];
        for (int i = 0; i < totalDisplayedItemCount; i++) {
            inventoryPage.clickOnItemName(i);
            detailItemNames[i] = itemPage.itemNameDetail.getText();
            detailItemDescriptions[i] = itemPage.itemDescriptionDetail.getText();
            String detailPriceString = itemPage.itemPriceDetail.getText();
            String detailPriceNoCurrency = detailPriceString.substring(detailPriceString.lastIndexOf("$") + 1);
            detailItemPrices[i] = Double.parseDouble(detailPriceNoCurrency);
            detailItemImgSrcs[i] = itemPage.itemImgDetail.getAttribute("src");
            // go back
            itemPage.clickOnBackToInventory();
        }
        //asertacije
        for (int i = 0; i < inventoryPage.itemNames.size(); i++) {
            Assert.assertEquals(displayedItemNames[i], detailItemNames[i]);
            Assert.assertEquals(displayedItemDescriptions[i], detailItemDescriptions[i]);
            Assert.assertEquals(displayedItemPrices[i], detailItemPrices[i]);
            Assert.assertEquals(displayedItemImgSrcs[i], detailItemImgSrcs[i]);
        }

    }

    @Test(priority = 30)
    public void userCanSeeAboutPage() {
        loginPage.clickOnHamburgerMenuIfDisplayed();
        sidebarPage.clickOnAboutLink();
        // asertacije
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }


    // sortiranje...


}
