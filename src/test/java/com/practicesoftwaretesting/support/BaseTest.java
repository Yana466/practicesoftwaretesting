package com.practicesoftwaretesting.support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import config.TestConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/** Shared Playwright lifecycle for Toolshop UI tests. */
public abstract class BaseTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void startBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(false)
                                .setSlowMo(TestConfig.SLOW_MO_MS)
                );
    }

    @BeforeEach
    void createPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closePage() {
        context.close();
    }

    @AfterAll
    static void stopBrowser() {
        browser.close();
        playwright.close();
    }
}
