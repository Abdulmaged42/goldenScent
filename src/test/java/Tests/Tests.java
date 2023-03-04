package Tests;

import base.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class Tests extends BaseTest {

    @Test(description = "Add an item to the cart.",priority = 1)
    public void addItemToCart() {


        String productName = productPage.getProductName();
        productPage.addToCart();
        productPage.openCart();
        productPage.verifyProductOnCard(productName);
    }

    @Test(description = "Verify that the product rating is more than 4 stars.",priority = 2)
    public void verifyStars() {
        double stars = productPage.getStars();
        assertTrue(stars > 4, "rating less than 4");

    }

    @Test(description = "Validate that if the product price is more than 300 than FREE Shipping text is present.",priority = 3)
    public void verifyFreeShippingText() {
        double price = productPage.getPrice();
        boolean isExist = productPage.verifyAppearanceOfFreeShippingText(price);
        assertTrue(isExist);
    }

    @Test(description = "The products in the similar products slider shows the products from the same brand.",priority = 4)
    public void verifyBrandAtSimilarProducts() {
        String brand = productPage.getBrand();
        productPage.scrollToSection("Similar products");
        List<String> brands = productPage.getBrands();
        boolean brandVerified = productPage.verifyBrands(brands, brand);
        assertTrue(brandVerified);
    }
}
