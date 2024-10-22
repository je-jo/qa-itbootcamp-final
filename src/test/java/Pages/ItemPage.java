package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement btnBackToInventory;

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement headerItemDetail;

    @FindBy(id = "add-to-cart")
    public WebElement btnAddToCart;

    public void clickOnBackToInventory() {
        btnBackToInventory.click();
    }

    public String getHeaderText() {
        return headerItemDetail.getText();
    }

    public void clickOnAddToCartButton() {
        btnAddToCart.click();
    }
}
