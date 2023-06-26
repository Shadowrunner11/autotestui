package com.testui.app;

import com.microsoft.playwright.*;

public class App{
    public static void main(String ...args){
        try(Playwright playwright = Playwright.create()){
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();

            page.navigate("https://google.com");

        }
    }
}
