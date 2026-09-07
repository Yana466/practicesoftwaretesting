package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.pages.CartPage;
import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.pages.ProductPage;
import com.practicesoftwaretesting.support.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchaseFlowTest extends BaseTest {

    @DisplayName("Search for Claw Hammer, add to cart, and verify only that product is in the cart")
    @Test
    void searchAndBuy() {
        String productName = "Claw Hammer";
        HomePage homePage = new HomePage(page);

        homePage.open();
        homePage.search(productName);
        ProductPage productPage = homePage.clickProductByName(productName);

        assertEquals(productName, productPage.getProductName(),
                "Product page should display the selected product name");

        productPage.addToCart();
        CartPage cartPage = productPage.goToCart();

        List<String> cartItems = cartPage.productTitles();
        assertEquals(1, cartItems.size(), "There should be only one product in the cart");
        assertEquals(productName, cartItems.get(0), "Unexpected product in the cart");
    }
}
