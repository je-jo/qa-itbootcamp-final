package Base;

import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.SidebarPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public static WebDriver driver;
    public static JavascriptExecutor js;
    public ExcelReader excelReader;
    public LoginMethod loginMethod;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public SidebarPage sidebarPage;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        excelReader = new ExcelReader("Data.xlsx");
        loginMethod = new LoginMethod();
        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        sidebarPage = new SidebarPage();
    }

    // helpers
    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isElemDisplayed(WebElement elem) {
        boolean isDisplayed = false;
        try {
            isDisplayed = elem.isDisplayed();
        } catch (Exception e) {
        }
        return isDisplayed;
    }




}
