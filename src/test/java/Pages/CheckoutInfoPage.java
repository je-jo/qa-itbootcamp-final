package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInfoPage extends BaseTest {

    public CheckoutInfoPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    public WebElement inputFirstName;

    @FindBy(id = "last-name")
    public WebElement inputLastName;

    @FindBy(id = "postal-code")
    public WebElement inputZipPostalCode;

    @FindBy(id = "continue")
    public WebElement btnContinue;

    @FindBy(css = "h3[data-test='error']")
    public WebElement msgError;

    @FindBy(id = "cancel")
    public WebElement btnCancel;

    public void enterFirstName(String firstName) {
        inputFirstName.clear();
        inputFirstName.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        inputLastName.clear();
        inputLastName.sendKeys(lastName);
    }

    public void enterZipPostalCode(String postalCode) {
        inputZipPostalCode.clear();
        inputZipPostalCode.sendKeys(postalCode);
    }

    public void clickContinueButton() {
        btnContinue.click();
    }

    public void clickCancelButton() {
        btnCancel.click();
    }




}
