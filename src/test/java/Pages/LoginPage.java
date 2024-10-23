package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    // elementi

    @FindBy(id = "user-name")
    public WebElement inputUsername;

    @FindBy(id = "password")
    public WebElement inputPassword;

    @FindBy(id = "login-button")
    public WebElement btnLogin;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement btnBurgerMenu;

    @FindBy(css = "h3[data-test='error']")
    public WebElement msgError;

    // metode

    public void enterUsername(String username) {
        inputUsername.clear();
        inputUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        btnLogin.click();
    }

    public void clickOnHamburgerMenuIfDisplayed() {
        try {
            btnBurgerMenu.click();
        } catch (Exception e) {
            System.out.println("Burger menu is not displayed.");
        }
    }

}
