package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static JavascriptExecutor js;
    public static WebDriverWait wait;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public SidebarPage sidebarPage;
    public ItemPage itemPage;
    public CartPage cartPage;
    public CheckoutInfoPage checkoutInfoPage;
    public CheckoutOverviewPage checkoutOverviewPage;
    public CheckoutCompletePage checkoutCompletePage;



    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        excelReader = new ExcelReader("Data.xlsx");
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        sidebarPage = new SidebarPage();
        itemPage = new ItemPage();
        cartPage = new CartPage();
        checkoutInfoPage = new CheckoutInfoPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }

    // helpers
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void logIn(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    public void logOut() {
        loginPage.clickOnHamburgerMenuIfDisplayed();
        sidebarPage.clickOnLogoutLink();
    }

    public boolean isElemDisplayed(WebElement elem) {
        boolean isDisplayed = false;
        try {
            isDisplayed = elem.isDisplayed();
        } catch (Exception e) {
        }
        return isDisplayed;
    }

    public void clearCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }




}
