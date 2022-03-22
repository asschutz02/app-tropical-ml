package com.example.tropical.selenium.finder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumFinder {

    public static WebElement barraPesquisa(WebDriver webDriver){
        return webDriver.findElement(By.className("nav-search-input"));
    }

    public static WebElement botaoPesquisa(WebDriver webDriver){
        return webDriver.findElement(By.className("nav-search-btn"));
    }

    public static WebElement numeroDePaginas(WebDriver webDriver){
        return webDriver.findElement(By.xpath("//li[contains(@class, 'andes-pagination__page-count')]"));
    }

    public static WebElement primeiroBotaoSeguinte(WebDriver webDriver){
        return webDriver.findElement(By.xpath("//a[contains(@class, 'andes-pagination__link ui-search-link')][1]"));
    }

    public static List<WebElement> botaoSeguinte(WebDriver webDriver){
        return webDriver.findElements(By.xpath("//a[contains(@class, 'andes-pagination__link ui-search-link')]"));
    }

    public static WebElement href(WebDriver webDriver){
        return webDriver.findElement(By.xpath("//a[contains(@class, 'ui-pdp-media__action ui-box-component__action')]"));
    }

    public static String nomeProduto(WebDriver webDriver){
        return webDriver.findElement(By.className("ui-pdp-title")).getText();
    }

    public static List<WebElement> listaPrecos(WebDriver webDriver){
        return webDriver.findElements(By.className("andes-money-amount__fraction"));
    }

    public static List<WebElement> linksProdutosGrid(WebDriver webDriver){
        return webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-result__content ui-search-link')]"));
    }

    public static List<WebElement> linksProdutosLine(WebDriver webDriver){
        return webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-item__group__element ui-search-link')]"));
    }

    public static List<WebElement> precosProdutosLink(WebDriver webDriver){
        return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));
    }
}
