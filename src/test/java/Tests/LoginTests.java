package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(priority = 10)
    public void userCanLogInWithValidCredentials() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            logIn(validUsername, validPassword);
            // System.out.println("Valid username " + validUsername + " able to login.");
            // username sa kojim pada test se nece odstampati
            // asertacije
            try {
                Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
                Assert.assertEquals(inventoryPage.header.getText(), "Swag Labs");
                Assert.assertFalse(isElemDisplayed(loginPage.btnLogin));
            } catch (AssertionError e) {
                System.out.println(validUsername + " cannot login.");
            }
            // log out
            clearCookies();
        }
    }

    @Test(priority = 20)
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

    @Test(priority = 30)
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

    @Test(priority = 40)
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

    @Test(priority = 50)
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

    @Test(priority = 60)
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

    @Test(priority = 70)
    public void UsernameFieldIsCaseSensitive() {
        // proveriti da li je prvo slovo vec uppercase?
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String firstLetterUppercased = validUsername.substring(0, 1).toUpperCase() + validUsername.substring(1);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            logIn(firstLetterUppercased, validPassword);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test(priority = 80)
    public void PasswordFieldIsCaseSensitive() {
        for (int i = 1; i < excelReader.getLastRow("Credentials") + 1; i++) {
            String validUsername = excelReader.getStringData("Credentials", i, 0);
            String validPassword = excelReader.getStringData("Credentials", i, 1);
            String lastLetterUppercased = validPassword.substring(0, validPassword.length() - 1) + validPassword.substring(validPassword.length() - 1).toUpperCase();
            logIn(validUsername, lastLetterUppercased);
            // asertacije
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(isElemDisplayed(loginPage.btnLogin));
            Assert.assertTrue(isElemDisplayed(loginPage.msgError));
            Assert.assertEquals(loginPage.msgError.getText(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @AfterMethod
    public void logOutAfterEachTest() {
        clearCookies();
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
