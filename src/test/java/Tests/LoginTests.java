package Tests;

import Base.BaseTest;
import Base.LoginMethod;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends LoginMethod {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void userCanLogInWithValidCredentials() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            // (locked_out_user obrisan jer pada test, videti posle...)
            logIn(validUsername, validPassword);
            loginPage.clickOnHamburgerMenuIfDisplayed();
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
            Assert.assertEquals(inventoryPage.getHeaderText(), "Swag Labs");
            Assert.assertTrue(isElemDisplayed(sidebarPage.linkLogout));
            // log out
            clearCookies();
        }
    }

    @Test
    public void UserCannotLogInWithInvalidUsername() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String invalidUsername = excelReader.getStringData("Credentials", i, 2);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            logIn(invalidUsername, validPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test
    public void UserCannotLogInWithInvalidPassword() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String invalidPassword = excelReader.getStringData("Credentials", i, 3);
            logIn(validUsername, invalidPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test
    public void UserCannotLogInWithInvalidUsernameAndPassword() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String invalidUsername = excelReader.getStringData("Credentials", i, 2);
            String invalidPassword = excelReader.getStringData("Credentials", i, 3);
            logIn(invalidUsername, invalidPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test
    public void UserCannotLogInWithoutEnteringUsername() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String blankUsername = "";
            String invalidPassword = excelReader.getStringData("Credentials", i, 3);
            logIn(blankUsername, invalidPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username is required");
        }
    }

    @Test
    public void UserCannotLogInWithoutEnteringPassword() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String blankPassword = "";
            logIn(validUsername, blankPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Password is required");
        }
    }

    @Test
    public void UsernameFieldIsCaseSensitive() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String allcapsUsername = validUsername.toUpperCase();
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            logIn(allcapsUsername, validPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test
    public void PasswordFieldIsCaseSensitive() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            String allcapsPassword = validPassword.toUpperCase();
            logIn(validUsername, allcapsPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    // helpers


    public void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }




}
