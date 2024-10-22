package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage extends BaseTest {

    public CheckoutOverviewPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    public List<WebElement> cartItemNames;

    @FindBy(id = "finish")
    public WebElement btnFinish;

    @FindBy(id = "cancel")
    public WebElement btnCancel;

    public void clickOnFinishButton() {
        btnFinish.click();
    }

    public void clickOnCancelButton() {
        btnCancel.click();
    }
}
