package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ItemPage extends BaseTest {

    public ItemPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "back-to-products")
    public WebElement btnBackToInventory;

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement itemNameDetail;

    @FindBy(css = ".inventory_details_desc.large_size")
    public WebElement itemDescriptionDetail;

    @FindBy(className = "inventory_details_price")
    public WebElement itemPriceDetail;

    @FindBy(css = "img[class='inventory_details_img']")
    public WebElement itemImgDetail;

    @FindBy(id = "add-to-cart")
    public WebElement btnAddToCart;

    @FindBy(className = "btn_inventory")
    public WebElement btnGeneral;

    public void clickOnBackToInventory() {
        btnBackToInventory.click();
    }

    public void clickOnAddToCartButton() {
        btnAddToCart.click();
    }
}
