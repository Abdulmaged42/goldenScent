package base;

import Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class BaseTest {
    private WebDriver driver;
    protected ProductPage productPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/resources/chromedriver.exe");
        driver = new ChromeDriver();
        goHome();
    }

    public void goHome(){
        driver.get("https://www.goldenscent.com/en/p/gucci-guilty-absolute-pour-homme-eau-de-parfum-for-men.html?action=prod&id=6108");
        productPage = new ProductPage(driver);
        productPage.closeForm();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
