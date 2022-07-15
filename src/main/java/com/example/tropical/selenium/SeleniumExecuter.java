package com.example.tropical.selenium;

import static com.example.tropical.selenium.decorator.AdSalesMLResponseDecorator.getNickName;
import static com.example.tropical.selenium.email.EmailJavaSender.emailJavaSender;
import static com.example.tropical.selenium.finder.SeleniumFinder.barraPesquisa;
import static com.example.tropical.selenium.finder.SeleniumFinder.botaoPesquisa;
import static com.example.tropical.selenium.finder.SeleniumFinder.href;
import static com.example.tropical.selenium.finder.SeleniumFinder.linksProdutosGrid;
import static com.example.tropical.selenium.finder.SeleniumFinder.linksProdutosLine;
import static com.example.tropical.selenium.finder.SeleniumFinder.listaCentavos;
import static com.example.tropical.selenium.finder.SeleniumFinder.listaPrecos;
import static com.example.tropical.selenium.finder.SeleniumFinder.marcaProduto;
import static com.example.tropical.selenium.finder.SeleniumFinder.numeroDePaginasList;
import static com.example.tropical.selenium.finder.SeleniumFinder.precosProdutosLink;
import static com.example.tropical.selenium.finder.SeleniumFinder.tituloAnuncio;
import static com.example.tropical.selenium.helper.SeleniumHelper.comparePricePMS;
import static com.example.tropical.selenium.helper.SeleniumHelper.containsOceanInAdTitle;
import static com.example.tropical.selenium.helper.SeleniumHelper.filterOtherBrands;
import static com.example.tropical.selenium.helper.SeleniumHelper.getLinksPage;
import static com.example.tropical.selenium.helper.SeleniumHelper.getNumberOfPageResults;
import static com.example.tropical.selenium.helper.SeleniumHelper.isNotProdutoParecido;
import static com.example.tropical.selenium.helper.SeleniumHelper.verifyIfIsOceanTech;
import static com.example.tropical.selenium.utils.SeleniumUtils.filterPrices;
import static javax.money.Monetary.getCurrency;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import com.example.tropical.selenium.excel.ExcelExecuter;
import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.products.ProductsEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class SeleniumExecuter {

	private final ExcelExecuter excelExecuter;

	public void executeSelenium(List<ProductsEntity> products) {
		System.out.println("Executando o programa");
		//        List<ProductsEntity> products = this.productsMapper.findAllProducts();
		System.out.println("products: " + products);
		List<AdSalesMLResponse> relatorio = new ArrayList<>();

		products.forEach(product -> {
			String firstPage = null;
			try {
				firstPage = searchProductByName(product.getName());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			List<String> links = null;
			try {
				links = linksPage(firstPage, product.getPrice());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			Optional<String> produtoSimilar = Optional.ofNullable(product.getProximo());

			List<AdSalesMLResponse> relatorioIndividual = getProductsInfo(links, product.getPrice(), product.getName(),
					produtoSimilar);

			relatorio.addAll(relatorioIndividual);
		});

		List<AdSalesMLResponse> relatorioFinal = filterOtherBrands(relatorio);

		try {
			excelExecuter.createExcel(relatorioFinal);
		} catch (IOException e) {
			e.printStackTrace();
		}

		emailJavaSender();
		//        emailSender();
	}

	public static List<String> linksPage(String firstPage, Double productPrice) throws MalformedURLException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shdevm-usage");

		System.out.println("links page");

//		WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"), options);
				WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);
		webDriver.navigate().to(firstPage);

		List<WebElement> listaPrecisaProximaPagina = new ArrayList<>();
		List<WebElement> allPrices = precosProdutosLink(webDriver);

		if (allPrices.size() != 0) {
			allPrices.removeIf(price -> !price.getCssValue("font-size").equals("24px"));
		}

		int allPriceTamanho;

		if (allPrices.size() == 0) {
			allPriceTamanho = 0;
		} else {
			allPriceTamanho = (allPrices.size() - 1);
		}

		if (allPrices.size() != 0) {
			CurrencyUnit real = getCurrency("BRL");
			String priceInt = allPrices.get(allPriceTamanho).getText().replace(".", "");
			String priceFinal = priceInt.replace(".", "");
			Integer price = Integer.valueOf(priceFinal);
			MonetaryAmount money = Money.of(price, real);
			comparePricePMS(money, real, productPrice, listaPrecisaProximaPagina, allPrices.get(allPriceTamanho));
		}

		List<String> pageLinks = new ArrayList<>();

		pageLinks.add(firstPage);

		List<WebElement> pageNumber = numeroDePaginasList(webDriver);

		if (listaPrecisaProximaPagina.size() != 0 && pageNumber.size() != 0) {
			System.out.println("VAI PRECISAR DE SEGUNDA PÁGINA");

			int numberPage = getNumberOfPageResults(pageNumber.get(0));

			System.out.println("numero de paginas: " + numberPage);

			getLinksPage(numberPage, pageLinks, webDriver);
		}

		webDriver.quit();

		List<String> noRepeat = pageLinks.stream().distinct().collect(Collectors.toList());

		return noRepeat;
	}

	public static String searchProductByName(String productName) throws MalformedURLException {

		System.out.println("nome dentro do método: " + productName);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

//		WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"), options);
				WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
		//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);

		String baseUrl = "https://www.mercadolivre.com.br/";

		webDriver.get(baseUrl);

		WebElement input = barraPesquisa(webDriver);

		input.sendKeys(productName);

		WebElement btn = botaoPesquisa(webDriver);

		btn.click();

		String firstPage = webDriver.getCurrentUrl();

		String linkNomeProduto = productName.replace(" ", "%20");

		String linkTratado = firstPage.replace("#D[A:".concat(linkNomeProduto).concat("]"), "");

		String linkPorOrdem = linkTratado.concat("_OrderId_PRICE_NoIndex_True");

		webDriver.close();
		webDriver.quit();

		return linkPorOrdem;

	}

	public static List<AdSalesMLResponse> getProductsInfo(List<String> pageLinks, Double price, String productName,
			Optional<String> produtoSimilar) {

		List<AdSalesMLResponse> finalList = new ArrayList<>();

		pageLinks.forEach(pgLink -> {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			WebDriver webDriver = null;
			try {
				webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
				//				                webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"),
				//				                options);
				//				webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
				//                webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			webDriver.manage().deleteAllCookies();
			webDriver.navigate().to(pgLink);

			try {
				createFinalObject(webDriver, price, finalList, productName, produtoSimilar);
			} catch (StaleElementReferenceException ex) {
				createFinalObject(webDriver, price, finalList, productName, produtoSimilar);
			}
			webDriver.close();
			webDriver.quit();
		});

		return finalList;
	}

	private static void createFinalObject(WebDriver webDriver, Double priceProduct,
			List<AdSalesMLResponse> finalList, String productName, Optional<String> produtoParecido) {

		List<String> linksResult = getProductInfo(webDriver, priceProduct);
		linksResult.forEach(link -> {
			webDriver.navigate().to(link);

			List<WebElement> href = (href(webDriver));

			String linkSeller = null;
			if (href.size() > 0) {
				linkSeller = href.get(0).getAttribute("href");
			}

			String adTitle = tituloAnuncio(webDriver);

			AdSalesMLResponse adResponse = new AdSalesMLResponse();
			adResponse.setAdTitle(adTitle);

			List<WebElement> marcaAnuncio = marcaProduto(webDriver);

			if (isNotProdutoParecido(produtoParecido, adTitle)) {
				if (marcaAnuncio.size() != 0) {
					if (verifyIfIsOceanTech(marcaAnuncio.get(0).getText(), adTitle)) {
						adResponse.setLinkAd(link);
						adResponse.setLinkSeller(linkSeller);
						if (!Objects.isNull(linkSeller)) {
							adResponse.setNickNameSeller(getNickName(linkSeller));
						}
						adResponse.setPms(priceProduct);
						adResponse.setProductName(productName);

						if (adResponse.getNickNameSeller().contains("=")) {
							String nickname = adResponse.getNickNameSeller();
							adResponse.setNickNameSeller(nickname.replace("=", "-"));
							if (adResponse.getNickNameSeller().contains("?")) {
								String nick = adResponse.getNickNameSeller();
								adResponse.setNickNameSeller(nick.replace("?", "-"));
							}
						}

						List<WebElement> prices = listaPrecos(webDriver);
						prices.forEach(price -> {
							String fontSize = price.getCssValue("font-size");
							if (fontSize.equals("36px")) {
								String bigPrice = price.getText();
								adResponse.setPrice(bigPrice);
							}
						});

						List<WebElement> centavos = listaCentavos(webDriver);

						if (centavos.size() > 0) {
							String precoAtual = adResponse.getPrice();

							String precoComCentavos = precoAtual.concat("," + centavos.get(0).getText());

							adResponse.setPrice(precoComCentavos);
						}
					}
				} else if (containsOceanInAdTitle(adTitle.toLowerCase()) && marcaAnuncio.size() == 0) {
					adResponse.setLinkAd(link);
					adResponse.setLinkSeller(linkSeller);
					adResponse.setNickNameSeller(getNickName(linkSeller));
					adResponse.setPms(priceProduct);
					adResponse.setProductName(productName);

					if (adResponse.getNickNameSeller().contains("=")) {
						String nickname = adResponse.getNickNameSeller();
						adResponse.setNickNameSeller(nickname.replace("=", "-"));
						if (adResponse.getNickNameSeller().contains("?")) {
							String nick = adResponse.getNickNameSeller();
							adResponse.setNickNameSeller(nick.replace("?", "-"));
						}
					}

					List<WebElement> prices = listaPrecos(webDriver);
					prices.forEach(price -> {
						String fontSize = price.getCssValue("font-size");
						if (fontSize.equals("36px")) {
							String bigPrice = price.getText();
							adResponse.setPrice(bigPrice);
						}
					});

					List<WebElement> centavos = listaCentavos(webDriver);

					if (centavos.size() > 0) {
						String precoAtual = adResponse.getPrice();

						String precoComCentavos = precoAtual.concat("," + centavos.get(0).getText());

						adResponse.setPrice(precoComCentavos);
					}
				}
			}
			finalList.add(adResponse);
		});
	}

	private static List<String> getProductInfo(WebDriver webDriver, Double price) {
		List<String> links = new ArrayList<>();

		List<WebElement> productLinksGrid = linksProdutosGrid(webDriver);

		List<WebElement> productLinksLine = linksProdutosLine(webDriver);

		if (productLinksGrid.size() > 0) {
			filterPrices(productLinksGrid, webDriver, links, price);
		} else {
			filterPrices(productLinksLine, webDriver, links, price);
		}
		return links;
	}
}
