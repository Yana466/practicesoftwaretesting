package com.practicesoftwaretesting.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Page object for the Practice Software Testing Toolshop cart / checkout page.
 */
public class CartPage {

    private static final String URL = "https://practicesoftwaretesting.com/checkout";

    private final Page page;
    private final Locator productTitles;
    private final Locator productQuantities;
    private final Locator productPrices;
    private final Locator linePrices;
    private final Locator cartTotal;
    private final Locator proceedToCheckoutButton;

    public CartPage(Page page) {
        this.page = page;
        this.productTitles = page.locator("[data-test='product-title']");
        this.productQuantities = page.locator("[data-test='product-quantity']");
        this.productPrices = page.locator("[data-test='product-price']");
        this.linePrices = page.locator("[data-test='line-price']");
        this.cartTotal = page.locator("[data-test='cart-total']");
        this.proceedToCheckoutButton = page.locator("[data-test='proceed-1']");
    }

    public void open() {
        page.navigate(URL);
    }

    public void waitForLoad() {
        page.waitForURL("**/checkout");
    }

    public List<String> productTitles() {
        productTitles.first().waitFor();
        return productTitles.allTextContents().stream()
                .map(title -> title.replace('\u00a0', ' ').trim())
                .toList();
    }

    public int itemCount() {
        return productTitles().size();
    }

    public String getFirstProductTitle() {
        List<String> titles = productTitles();
        if (titles.isEmpty()) {
            throw new IllegalStateException("Cart is empty, cannot get first product title");
        }
        return titles.get(0);
    }

    public int getProductQuantity(int index) {
        productQuantities.nth(index).waitFor();
        return Integer.parseInt(productQuantities.nth(index).inputValue().trim());
    }

    public String getProductPrice(int index) {
        return productPrices.nth(index).textContent().trim();
    }

    public String getLinePrice(int index) {
        return linePrices.nth(index).textContent().trim();
    }

    public String getCartTotal() {
        return cartTotal.textContent().trim();
    }

    public boolean isProductInCart(String productName) {
        return productTitles().stream()
                .anyMatch(title -> title.equalsIgnoreCase(productName.trim()));
    }
}
