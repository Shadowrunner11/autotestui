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
         page.navigate("https://www.wikipedia.org/");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("playwright");
        page.locator("input[name=\"search\"]").press("Enter");
        page.waitForTimeout(10_000);
        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
    }
}
