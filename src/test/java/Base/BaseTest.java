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

    public int dataItemCount;
    public String[] dataItemNames;
    public String[] dataItemDescriptions;
    public double[] dataItemPrices;
    public String[] dataItemImgSrcs;

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

        dataItemCount = excelReader.getLastRow("ItemsData");
        dataItemNames = new String[dataItemCount];
        dataItemDescriptions = new String[dataItemCount];
        dataItemPrices = new double[dataItemCount];
        dataItemImgSrcs = new String[dataItemCount];
        getDataFromExcel();
    }

    // helpers
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void getDataFromExcel() {
        for (int i = 0; i < dataItemCount; i++) {
            dataItemNames[i] = excelReader.getStringData("ItemsData", i + 1, 1);
            dataItemDescriptions[i] = excelReader.getStringData("ItemsData", i + 1, 2);
            dataItemPrices[i] = Double.valueOf(excelReader.getStringData("ItemsData", i + 1, 3));
            dataItemImgSrcs[i] = "https://www.saucedemo.com" + excelReader.getStringData("ItemsData", i + 1, 4);
        }
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
