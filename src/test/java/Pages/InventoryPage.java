package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement header;

    // metode

    public String getHeaderText() {
        return header.getText();
    }

}
