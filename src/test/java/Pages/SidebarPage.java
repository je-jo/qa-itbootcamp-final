package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SidebarPage extends BaseTest {

    public SidebarPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "logout_sidebar_link")
    public WebElement linkLogout;

    @FindBy(id = "about_sidebar_link")
    public WebElement linkAbout;

    // metode

    public void clickOnLogoutLink() {
        linkLogout.click();
    }

    public void clickOnAboutLink() {
        linkAbout.click();
    }



}
