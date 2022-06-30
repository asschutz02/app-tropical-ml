package com.example.tropical.selenium.finder;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumFinder {

	public static WebElement barraPesquisa(WebDriver webDriver) {
		return webDriver.findElement(By.className("nav-search-input"));
	}

	public static WebElement botaoPesquisa(WebDriver webDriver) {
		return webDriver.findElement(By.className("nav-search-btn"));
	}

	public static List<WebElement> numeroDePaginasList(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("//li[contains(@class, 'andes-pagination__page-count')]"));
	}

	public static WebElement primeiroBotaoSeguinte(WebDriver webDriver) {
		return webDriver.findElement(By.xpath("//a[contains(@class, 'andes-pagination__link ui-search-link')][1]"));
	}

	public static List<WebElement> href(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("/html/body/main/div/div[4]/div/div[1]/div/div[2]/div/div/a"));
	}

	public static String tituloAnuncio(WebDriver webDriver) {
		return webDriver.findElement(By.className("ui-pdp-title")).getText();
	}

	public static List<WebElement> listaPrecos(WebDriver webDriver) {
		return webDriver.findElements(By.className("andes-money-amount__fraction"));
	}

	public static List<WebElement> linksProdutosGrid(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-result__content ui-search-link')]"));
	}

	public static List<WebElement> linksProdutosLine(WebDriver webDriver) {
		return webDriver.findElements(
				By.xpath("//a[contains(@class, 'ui-search-item__group__element ui-search-link')]"));
	}

	public static List<WebElement> precosProdutosLink(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));
	}

	public static List<WebElement> marcaProduto(WebDriver webDriver) {
		return webDriver.findElements(
				By.xpath("/html/body/main/div/div[4]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[1]/td/span"));
	}
}
