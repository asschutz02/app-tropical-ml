package com.example.tropical.selenium;

import static com.example.tropical.selenium.decorator.AdSalesMLResponseDecorator.getNickName;
import static com.example.tropical.selenium.email.EmailJavaSender.emailJavaSender;
import static com.example.tropical.selenium.finder.SeleniumFinder.barraPesquisa;
import static com.example.tropical.selenium.finder.SeleniumFinder.botaoPesquisa;
import static com.example.tropical.selenium.finder.SeleniumFinder.href;
import static com.example.tropical.selenium.finder.SeleniumFinder.linksProdutosGrid;
import static com.example.tropical.selenium.finder.SeleniumFinder.linksProdutosLine;
import static com.example.tropical.selenium.finder.SeleniumFinder.listaPrecos;
import static com.example.tropical.selenium.finder.SeleniumFinder.nomeProduto;
import static com.example.tropical.selenium.finder.SeleniumFinder.numeroDePaginas;
import static com.example.tropical.selenium.helper.SeleniumHelper.getLinksPage;
import static com.example.tropical.selenium.helper.SeleniumHelper.getNumberOfPageResults;
import static com.example.tropical.selenium.utils.SeleniumUtils.filterPrices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
                links = linksPage(firstPage);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            List<AdSalesMLResponse> relatorioIndividual = getProductsInfo(links, product.getPrice());

            relatorio.addAll(relatorioIndividual);
        });

        try {
            excelExecuter.createExcel(relatorio);
        } catch (IOException e) {
            e.printStackTrace();
        }

        emailJavaSender();
//        emailSender();
    }

    public static List<String> linksPage(String firstPage) throws MalformedURLException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shdevm-usage");


        System.out.println("links page");

        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"), options);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
//        WebDriver webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);
        webDriver.navigate().to(firstPage);

        WebElement pageNumber = numeroDePaginas(webDriver);

        int numberPage = getNumberOfPageResults(pageNumber);

        System.out.println("numero de paginas: " + numberPage);

        List<String> pageLinks = new ArrayList<>();

        pageLinks.add(firstPage);

        List<String> finalList = getLinksPage(numberPage, pageLinks, webDriver);

        webDriver.quit();

        return finalList;
    }

    public static String searchProductByName(String productName) throws MalformedURLException {

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

        return linkPorOrdem;

    }

    public static List<AdSalesMLResponse> getProductsInfo(List<String> pageLinks, Double price) {

        List<AdSalesMLResponse> finalList = new ArrayList<>();

        pageLinks.forEach(pgLink -> {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            WebDriver webDriver = null;
            try {
//                webDriver = new RemoteWebDriver(new URL("http://192.168.65.4:4444"), options);
//                webDriver = new RemoteWebDriver(new URL("http://172.17.0.4:4444"), options);
                webDriver = new RemoteWebDriver(new URL("http://172.17.0.2:4444"), options);
//                webDriver = new RemoteWebDriver(new URL("http://172.17.0.3:4444"), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            webDriver.manage().deleteAllCookies();
            webDriver.navigate().to(pgLink);

            try {
                createFinalObject(webDriver, price, finalList);
            } catch (StaleElementReferenceException ex) {
                createFinalObject(webDriver, price, finalList);
            }
            webDriver.close();
            webDriver.quit();
        });

        return finalList;
    }

    private static void createFinalObject(WebDriver webDriver, Double priceProduct, List<AdSalesMLResponse> finalList) {
        List<String> linksResult = getProductInfo(webDriver, priceProduct);
        linksResult.forEach(link -> {
            webDriver.navigate().to(link);

            WebElement href = href(webDriver);
            String linkSeller = href.getAttribute("href");

            String productName = nomeProduto(webDriver);

            AdSalesMLResponse adResponse = new AdSalesMLResponse();
            adResponse.setProductName(productName);
            adResponse.setLinkAd(link);
            adResponse.setLinkSeller(linkSeller);
            adResponse.setNickNameSeller(getNickName(linkSeller));
            adResponse.setPms(priceProduct);

            if(adResponse.getNickNameSeller().contains("=")) {
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
                    Double bigPrice = Double.valueOf(price.getText());
                    adResponse.setPrice(bigPrice);
                }
            });

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
