package com.example.tropical.selenium.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import org.openqa.selenium.WebElement;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SeleniumHelper {

    public static Integer getNumberOfPageResults(WebElement pageNumber) {
        String onlyNumber = pageNumber.getText().replace(" ", "");

        if (onlyNumber.length() == 3) {
            String justTheNumber = onlyNumber.substring(2);
            return Integer.valueOf(justTheNumber);
        } else {
            String justTheNumber = onlyNumber.substring(2, 4);
            return Integer.valueOf(justTheNumber);
        }
    }

    public static Boolean comparePrice(MonetaryAmount moneyML, CurrencyUnit real, Double realPrice){

        MonetaryAmount moneyReal = Money.of(realPrice, real);

        MonetaryAmount halfMoney = moneyReal.divide(2L);

        MonetaryAmount quarterMoney = halfMoney.divide(2L);

        MonetaryAmount threeQuarter = halfMoney.add(quarterMoney);

        return moneyML.isLessThan(moneyReal) && moneyML.isGreaterThan(threeQuarter);
    }
}
