package com.example.tropical.selenium.finder;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
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
		return webDriver.findElement(By.xpath("/html/body/main/div/div[2]/section/div[9]/ul/li[3]/a"));
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

	public static List<WebElement> listaCentavos(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("//*[@id=\"price\"]/div/div[1]/span/span[5]"));
	}

	public static List<WebElement> linksProdutosGrid(WebDriver webDriver) {
		return webDriver.findElements(By.xpath("//a[contains(@class, 'ui-search-result__content ui-search-link')]"));
	}

	public static List<WebElement> linksProdutosLine(WebDriver webDriver) {
		return webDriver.findElements(
				By.xpath("//a[contains(@class, 'ui-search-item__group__element ui-search-link')]"));
	}

	public static List<WebElement> precosProdutosLink(WebDriver webDriver) {
//		return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));
		return webDriver.findElements(By.xpath("/html/body/main/div/div[2]/section/ol[1]/li[1]/div/div/div[2]/div[1]/div[2]/div/div/div/span[1]/span[2]/span[2]"));
//		#root-app > div > div.ui-search-main.ui-search-main--exhibitor.ui-search-main--only-products.ui-search-main--with-topkeywords.shops__search-main > section > ol:nth-child(8) > li:nth-child(1) > div > div > div.ui-search-result__content > div.ui-search-result__content-wrapper.shops__result-content-wrapper > div.ui-search-item__group.ui-search-item__group--price.shops__items-group > div > div > div > span.price-tag.ui-search-price__part.shops__price-part > span.price-tag-amount > span.price-tag-fraction
//		#root-app > div > div.ui-search-main.ui-search-main--exhibitor.ui-search-main--only-products.ui-search-main--with-topkeywords.shops__search-main > section > ol:nth-child(8) > li:nth-child(2) > div > div > div.ui-search-result__content > div.ui-search-result__content-wrapper.shops__result-content-wrapper > div.ui-search-item__group.ui-search-item__group--price.shops__items-group > div > div > div > span.price-tag.ui-search-price__part.shops__price-part > span.price-tag-amount > span.price-tag-fraction
//      #root-app > div > div.ui-search-main.ui-search-main--exhibitor.ui-search-main--only-products.ui-search-main--with-topkeywords.shops__search-main > section > ol:nth-child(9) > li:nth-child(1) > div > div > div.ui-search-result__content > div.ui-search-result__content-wrapper.shops__result-content-wrapper > div.ui-search-item__group.ui-search-item__group--price.shops__items-group > div > div > div > span.price-tag.ui-search-price__part.shops__price-part > span.price-tag-amount > span.price-tag-fraction
//      #root-app > div > div.ui-search-main.ui-search-main--without-header.ui-search-main--only-products.shops__search-main > section > ol > li:nth-child(33) > div > div > div.ui-search-result__content-wrapper.shops__result-content-wrapper > div.ui-search-result__content-columns.shops__content-columns > div.ui-search-result__content-column.ui-search-result__content-column--left.shops__content-columns-left > div > div > div > div > span.price-tag.ui-search-price__part.shops__price-part > span.price-tag-amount > span.price-tag-fraction
//		var links1 = precosProdutosLink1(webDriver);
//		var links2 = precosProdutosLink2(webDriver);
//
//		if (!links1.isEmpty()) {
//			return links1;
//		} else {
//			return links2;
//		}

//			return webDriver.findElements(By.className("price-tag-fraction"));
	}

	public static List<WebElement> precosProdutosLink1(WebDriver webDriver) {
//		return webDriver.findElements(By.xpath("/html/body/main/div/div[2]/section/ol[1]/li[1]/div/div/div[2]/div/div[2]/div/div/div/span[1]/span[2]/span[2]"));
		return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));
	}

	public static List<WebElement> precosProdutosLink2(WebDriver webDriver) {
//		return webDriver.findElements(By.xpath("/html/body/main/div/div[2]/section/ol/li[1]/div/div/div[2]/div[2]/div[1]/div/div/div/div/span[1]/span[2]/span[2]"));
		return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));

	}

	public static List<WebElement> precosProdutosLink3(WebDriver webDriver) {
//		return webDriver.findElements(By.xpath("/html/body/main/div/div[2]/section/ol[1]/li[1]/div/div/div[2]/div[1]/div[2]/div/div/div/span[1]/span[2]/span[2]"));
		return webDriver.findElements(By.xpath("//span[contains(@class, 'price-tag-fraction')]"));

	}

	public static List<WebElement> marcaProduto(WebDriver webDriver) {
		return webDriver.findElements(
				By.xpath("/html/body/main/div/div[4]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[1]/td/span"));
	}
}
