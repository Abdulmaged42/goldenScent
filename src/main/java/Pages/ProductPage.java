package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductPage {
    private WebDriver driver;
    private By addCartLocator = By.xpath("//span[contains(text(),'Add to cart')]");
    private By closeFormLocator = By.xpath("//button[@class='ab-close-button']");
    private By viwCartLocator = By.xpath("//button[@aria-label='View my cart']");
    private By productNameLocator = By.xpath("//div[@class='title-head']/div/div/h1");
    private By cartCountLocator = By.xpath("//span[@data-testid='minicartCount']");
    private By starsLocator = By.xpath("//span[@class='avg-rating']");
    private By priceLocator = By.xpath("(//div[@class='savings']/span)[2]/.");
    private By freeShippingLocator = By.xpath("//span[contains(text(),'free shipping')]");
    private By brandLocator = By.xpath("//h3[@class='brand-name']");
    private By itemsBrandsLocator = By.xpath("//div[contains(text(),'Similar products')]/..//ul[@class='p-l-ul-0']/li//div[@class='col-xs-12 p-name']");

    public ProductPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void clickIn(By locator) {
        waitFor(locator);
        driver.findElement(locator).click();
    }

    public void addToCart() {
        clickIn(addCartLocator);
    }

    public void closeForm() {
        waitFor(closeFormLocator);
        clickIn(closeFormLocator);
    }

    public String getProductName() {
        waitFor(productNameLocator);
        return driver.findElement(productNameLocator).getText();
    }

    public void openCart() {
        waitFor(cartCountLocator);
        clickIn(viwCartLocator);
    }

    public double getStars() {
        waitFor(starsLocator);
        String text = driver.findElement(starsLocator).getText();
        double stars = Double.parseDouble(text);
        return stars;
    }

    public boolean verifyProductOnCard(String productName) {
        By element = By.xpath("//div[contains(text(),'" + productName + "')]");
        waitFor(element);
        return driver.findElement(element).isDisplayed();
    }

    public double getPrice() {
        String total = driver.findElement(priceLocator).getText();
        System.out.println("total " + total);
        String[] parts = total.split(" ");
        double number = 0.0;
        try {
            number = Double.parseDouble(parts[0]);

        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } catch (RangeException e) {
            throw e;
        } finally {
            System.out.println("Thank you for using the program!");
        }
        return number;
    }

    public boolean verifyAppearanceOfFreeShippingText(double price) {
        waitFor(freeShippingLocator);
        System.out.println("price " + price);
        boolean isExist = false;
        if (price > 300) {
            isExist = driver.findElement(freeShippingLocator).isDisplayed();
        }
        return isExist;

    }

    public String getBrand() {
        waitFor(brandLocator);
        return driver.findElement(brandLocator).getText();
    }

    public void scrollToSection(String section) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//div[contains(text(),'" + section + "')]"));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public List<String> getBrands() {
        waitFor(itemsBrandsLocator);
        List<WebElement> elements = driver.findElements(itemsBrandsLocator);
        return elements.stream().map(e -> e.getText().split("\n")[0]).collect(Collectors.toList());
    }

    public boolean verifyBrands(List<String> brands, String actualBrand) {
        String element2 = actualBrand.toLowerCase().replaceAll("\\s", "");
        for (String brand : brands) {
            String element1 = brand.toLowerCase().replaceAll("\\s", "");
            if (!element1.equals(element2)) {
                return false;
            }
        }
        return true;
    }


}
