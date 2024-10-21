package Base;

public class LoginMethod extends BaseTest {

    public void logIn(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
}
