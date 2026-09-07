package com.practicesoftwaretesting.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page object for the Practice Software Testing Toolshop product details page.
 */
public class ProductPage {

    private final Page page;
    private final Locator productName;
    private final Locator unitPrice;
    private final Locator quantityInput;
    private final Locator addToCartButton;
    private final Locator cartLink;
    private final Locator cartQuantity;

    public ProductPage(Page page) {
        this.page = page;
        this.productName = page.locator("[data-test='product-name']");
        this.unitPrice = page.locator("[data-test='unit-price']");
        this.quantityInput = page.locator("[data-test='quantity']");
        this.addToCartButton = page.locator("[data-test='add-to-cart']");
        this.cartLink = page.locator("[data-test='nav-cart']");
        this.cartQuantity = page.locator("[data-test='cart-quantity']");
    }

    public String getProductName() {
        return productName.textContent().trim();
    }

    public String getUnitPrice() {
        return unitPrice.textContent().trim();
    }

    public void setQuantity(int quantity) {
        quantityInput.fill(String.valueOf(quantity));
    }

    public void addToCart() {
        addToCartButton.click();
        cartQuantity.waitFor();
    }

    public CartPage goToCart() {
        cartLink.click();
        return new CartPage(page);
    }

    public CartPage openCart() {
        return goToCart();
    }

    public int getCartQuantity() {
        cartQuantity.waitFor();
        return Integer.parseInt(cartQuantity.textContent().trim());
    }
}
