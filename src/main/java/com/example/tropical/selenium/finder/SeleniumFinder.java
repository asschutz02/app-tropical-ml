package com.example.tropical.selenium.finder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumFinder {

    public static WebElement findBarraPesquisa(WebDriver webDriver){
        return webDriver.findElement(By.className("nav-search-input"));
    }
}
