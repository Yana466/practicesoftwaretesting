package com.practicesoftwaretesting.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Page object for the Practice Software Testing Toolshop home page.
 */
public class HomePage {

    private static final String URL = "https://practicesoftwaretesting.com/";

    private final Page page;
    private final Locator searchInput;
    private final Locator searchButton;
    private final Locator productNames;
    private final Locator searchCompleted;

    public HomePage(Page page) {
        this.page = page;
        this.searchInput = page.locator("[data-test='search-query']");
        this.searchButton = page.locator("[data-test='search-submit']");
        this.productNames = page.locator("[data-test='product-name']");
        this.searchCompleted = page.locator("[data-test='search_completed']");
    }

    public void open() {
        page.navigate(URL);
    }

    public void search(String query) {
        searchInput.scrollIntoViewIfNeeded();
        searchInput.fill(query);
        searchButton.click();
        searchCompleted.waitFor();
    }

    public List<String> productTitles() {
        return productNames.allTextContents();
    }


    public Locator productByName(String productName) {
        return productNames.getByText(productName, new Locator.GetByTextOptions().setExact(true));
    }

    public ProductPage clickProductByName(String productName) {
        productByName(productName).click();
        return new ProductPage(page);
    }
}
