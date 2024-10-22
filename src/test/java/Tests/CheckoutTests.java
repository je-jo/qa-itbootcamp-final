package Tests;

import Base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        logIn(excelReader.getStringData("Credentials", 1, 0), excelReader.getStringData("Credentials", 1, 1));
        inventoryPage.btnsAddToCart.get(0).click();
        inventoryPage.clickOnCartLink();
    }

    @Test
    public void userCanPurchaseItem() {
        cartPage.clickOnCheckoutLink();
        checkoutInfoPage.enterFirstName("Petar");
        checkoutInfoPage.enterLastName("Panic");
        checkoutInfoPage.enterZipPostalCode("11070");
        checkoutInfoPage.clickContinueButton();

    }


}
