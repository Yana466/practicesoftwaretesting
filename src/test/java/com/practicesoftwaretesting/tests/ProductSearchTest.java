package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.support.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductSearchTest extends BaseTest {

    @DisplayName("Search for hammer shows only hammer products")
    @Test
    void searchForHammerShowsOnlyHammers() {
        HomePage homePage = new HomePage(page);

        homePage.open();
        homePage.search("hammer");

        List<String> titles = homePage.productTitles();
        assertFalse(titles.isEmpty(), "Search should return at least one product");
        for (String title : titles) {
            assertTrue(
                    title.toLowerCase().contains("hammer"),
                    "Expected product title to contain 'hammer' but was: " + title
            );
        }
    }
}
