package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item_name")
    public List<WebElement> cartItemNames;

    @FindBy(className = "cart_button")
    public List<WebElement> btnsRemoveFromCart;

    @FindBy(id = "checkout")
    public WebElement linkCheckout;

    public void removeItemFromCart(int index) {
        btnsRemoveFromCart.get(index).click();
    }

    public void removeAllFromCart() {
        for (int i = btnsRemoveFromCart.size() - 1; i >= 0; i--) {
            btnsRemoveFromCart.get(i).click();
        }
    }

    public void clickOnCheckoutLink() {
        linkCheckout.click();
    }

}
