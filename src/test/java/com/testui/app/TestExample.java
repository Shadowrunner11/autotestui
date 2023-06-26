package com.testui.app;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestExample {

    private static Playwright playwright;
    private static Browser browser;

    private  BrowserContext context;
    private Page page;

    @BeforeAll
    static void launchBrowser(){
        playwright = Playwright.create();
        browser = playwright
            .chromium()
            .launch(new BrowserType.LaunchOptions().setHeadless(false));
    
    }

    @AfterAll
    static void closeBrowser(){
        playwright.close();
    }

    @BeforeEach
    void createContext(){
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void shouldAnswerWithTrue(){
        String selector = "input[name=\"search\"]";

        String baseUrl = "https://www.wikipedia.org/";

        page.navigate(baseUrl);
        page.locator(selector).click();
        page.locator(selector).fill("playwright");
        page.locator(selector).press("Enter");
        page.waitForTimeout(10_000);
        assertEquals(baseUrl + "/wiki/Playwright", page.url());
    }
}
