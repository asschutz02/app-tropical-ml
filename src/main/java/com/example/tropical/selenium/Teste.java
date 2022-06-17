package com.example.tropical.selenium;

import static com.example.tropical.selenium.finder.SeleniumFinder.barraPesquisa;
import static com.example.tropical.selenium.finder.SeleniumFinder.botaoPesquisa;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Teste {

	public static void main(String[] args) throws MalformedURLException {
		String productName = "bomba ac 6000 ocean tech";
		System.out.println("nome dentro do método: " + productName);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);

		String baseUrl = "https://www.mercadolivre.com.br/";

		webDriver.get(baseUrl);

		WebElement input = barraPesquisa(webDriver);

		input.sendKeys(productName);

		WebElement btn = botaoPesquisa(webDriver);

		btn.click();

		String firstPage = webDriver.getCurrentUrl();

		String linkNomeProduto = productName.replace(" ", "%20");
		System.out.println("linkNomeProduto: " + linkNomeProduto);

		System.out.println("link: " + firstPage);

		String linkTratado = firstPage.replace("#D[A:".concat(linkNomeProduto).concat("]"), "");

		System.out.println("linkTratado: " + linkTratado);

		String linkPorOrdem = linkTratado.concat("_OrderId_PRICE_NoIndex_True");

		System.out.println("linkPorOrdem: " + linkPorOrdem);

		webDriver.close();
		webDriver.quit();
	}
}
