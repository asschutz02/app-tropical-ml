package com.example.tropical.selenium.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.util.List;

import static com.example.tropical.selenium.finder.SeleniumFinder.precosProdutosLink;
import static com.example.tropical.selenium.finder.SeleniumFinder.precosProdutosLink1;
import static com.example.tropical.selenium.finder.SeleniumFinder.precosProdutosLink2;
import static com.example.tropical.selenium.finder.SeleniumFinder.precosProdutosLink3;
import static com.example.tropical.selenium.helper.SeleniumHelper.comparePrice;
import static javax.money.Monetary.getCurrency;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SeleniumUtils {

    public static void filterPrices(List<WebElement> productLinks, WebDriver webDriver, List<String> links, Double priceProduct){
        List<WebElement> allPrices = precosProdutosLink(webDriver);
//        List<WebElement> allPrices;
//        List<WebElement> allPrices1 = precosProdutosLink1(webDriver);
//        List<WebElement> allPrices2 = precosProdutosLink2(webDriver);
//        List<WebElement> allPrices3 = precosProdutosLink3(webDriver);
//
//        if (!allPrices1.isEmpty()) {
//            allPrices = allPrices1;
//        } else if (!allPrices2.isEmpty()) {
//            allPrices = allPrices2;
//        } else {
//            allPrices = allPrices3;
//        }

        if (!allPrices.isEmpty()) {
            allPrices.removeIf(price -> !price.getCssValue("font-size").equals("24px"));
        }

        allPrices.forEach(priceML -> {
            CurrencyUnit real = getCurrency("BRL");
            String priceInt = priceML.getText().replace(".", "");
            String priceFinal = priceInt.replace(".", "");
            Integer price = Integer.valueOf(priceFinal);

            MonetaryAmount money = Money.of(price, real);


            if(comparePrice(money, real, priceProduct)){
                int indexPrice = allPrices.indexOf(priceML);

                if(!productLinks.isEmpty()) {
                    if(productLinks.get(indexPrice).getAttribute("href").contains("MLB")) {
                        links.add(productLinks.get(indexPrice).getAttribute("href"));
                    }
                }
            }
        });
    }
}
