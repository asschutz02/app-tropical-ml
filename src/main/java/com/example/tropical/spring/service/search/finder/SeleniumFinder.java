package com.example.tropical.spring.service.search.finder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SeleniumFinder {

	public static WebElement barraPesquisa(WebDriver webDriver) {
		return webDriver.findElement(By.className("nav-search-input"));
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
		return webDriver.findElements(By.xpath("/html/body/main/div/div[2]/section/ol[1]/li[1]/div/div/div[2]/div[1]/div[2]/div/div/div/span[1]/span[2]/span[2]"));
	}


	public static List<WebElement> marcaProduto(WebDriver webDriver) {
		return webDriver.findElements(
				By.xpath("/html/body/main/div/div[4]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[1]/td/span"));
	}
}
