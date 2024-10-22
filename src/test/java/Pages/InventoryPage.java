package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "app_logo")
    public WebElement header;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemNames;

    @FindBy(className = "inventory_item_desc")
    public List<WebElement> itemDescriptions;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(css = "img[class='inventory_item_img']")
    public List<WebElement> itemImages;

    @FindBy(css = "button[id^='add-to-cart']")
    public List<WebElement> btnsAddToCart;

    @FindBy(css = "button[id^='remove']")
    public List<WebElement> btnsRemoveFromCart;

    @FindBy(className = "shopping_cart_badge")
    public WebElement badgeCart;

    @FindBy(className = "shopping_cart_link")
    public WebElement linkCart;



    // metode

    public String getHeaderText() {
        return header.getText();
    }

    public void clickOnItemName(int index) {
        itemNames.get(index).click();
    }

    public void clickOnCartLink() {
        linkCart.click();
    }



}
