package com.example.tropical.selenium.helper;

import static com.example.tropical.selenium.finder.SeleniumFinder.primeiroBotaoSeguinte;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.tropical.selenium.model.AdSalesMLResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SeleniumHelper {

	public static Integer getNumberOfPageResults(WebElement pageNumber) {
		String onlyNumber = pageNumber.getText().replace(" ", "");

		String justTheNumber;
		if (onlyNumber.length() == 3) {
			justTheNumber = onlyNumber.substring(2);
		} else {
			justTheNumber = onlyNumber.substring(2, 4);
		}
		return Integer.valueOf(justTheNumber);
	}

	public static void comparePricePMS(MonetaryAmount moneyML, CurrencyUnit real, Double realPrice,
			List<WebElement> listaPrecisaProximaPagina, WebElement price) {

		MonetaryAmount moneyReal = Money.of(realPrice, real);

		if (moneyML.isLessThan(moneyReal)) {
			listaPrecisaProximaPagina.add(price);
		}
	}

	public static Boolean comparePrice(MonetaryAmount moneyML, CurrencyUnit real, Double realPrice) {

		MonetaryAmount moneyReal = Money.of(realPrice, real);

		MonetaryAmount halfMoney = moneyReal.divide(2L);

		MonetaryAmount quarterMoney = halfMoney.divide(2L);

		MonetaryAmount threeQuarter = halfMoney.add(quarterMoney);

		return moneyML.isLessThan(moneyReal) && moneyML.isGreaterThan(threeQuarter);
	}

	public static void getLinksPage(int numberPage, List<String> pageLinks, WebDriver webDriver) {
		if (numberPage > 1) {
			WebElement btnNextLink = primeiroBotaoSeguinte(webDriver);
			String segundoLink = btnNextLink.getAttribute("href");
			webDriver.quit();
			pageLinks.add(segundoLink);

			if (numberPage >= 3) {
				String third = segundoLink.replace("49", "97");
				pageLinks.add(third);
			}
			if (numberPage >= 4) {
				String fourth = segundoLink.replace("49", "145");
				pageLinks.add(fourth);
			}
			if (numberPage >= 5) {
				String fifth = segundoLink.replace("49", "193");
				pageLinks.add(fifth);
			}
			if (numberPage >= 6) {
				String sixth = segundoLink.replace("49", "241");
				pageLinks.add(sixth);
			}
			if (numberPage >= 7) {
				String seventh = segundoLink.replace("49", "289");
				pageLinks.add(seventh);
			}
			if (numberPage >= 8) {
				String eighth = segundoLink.replace("49", "337");
				pageLinks.add(eighth);
			}
			if (numberPage >= 9) {
				String nineth = segundoLink.replace("49", "385");
				pageLinks.add(nineth);
			}
			if (numberPage >= 10) {
				String tenth = segundoLink.replace("49", "433");
				pageLinks.add(tenth);
			}
			if (numberPage >= 11) {
				String eleven = segundoLink.replace("49", "481");
				pageLinks.add(eleven);
			}
			if (numberPage >= 12) {
				String twelve = segundoLink.replace("49", "529");
				pageLinks.add(twelve);
			}
			if (numberPage >= 13) {
				String treze = segundoLink.replace("49", "577");
				pageLinks.add(treze);
			}
			if (numberPage >= 14) {
				String quatorze = segundoLink.replace("49", "625");
				pageLinks.add(quatorze);
			}
			if (numberPage >= 15) {
				String quinze = segundoLink.replace("49", "673");
				pageLinks.add(quinze);
			}
			if (numberPage >= 16) {
				String dezesseis = segundoLink.replace("49", "721");
				pageLinks.add(dezesseis);
			}
			if (numberPage >= 17) {
				String dezessete = segundoLink.replace("49", "769");
				pageLinks.add(dezessete);
			}
			if (numberPage >= 18) {
				String dezoito = segundoLink.replace("49", "817");
				pageLinks.add(dezoito);
			}
			if (numberPage >= 19) {
				String dezenove = segundoLink.replace("49", "865");
				pageLinks.add(dezenove);
			}
			if (numberPage >= 20) {
				String vinte = segundoLink.replace("49", "913");
				pageLinks.add(vinte);
			}
		}
		pageLinks.forEach(link -> System.out.println("LINKS: " + link));
		System.out.println("tamanho lista de links: " + pageLinks.size());
	}

	public static List<AdSalesMLResponse> filterOtherBrands(List<AdSalesMLResponse> relatorio) {
		return relatorio.stream().filter(SeleniumHelper::validateBrands).collect(Collectors.toList());
	}

	private static Boolean validateBrands(AdSalesMLResponse relatorioObject) {
		Optional<String> linkAd = Optional.ofNullable(relatorioObject.getLinkAd());
		return linkAd.isPresent();
	}

	public static Boolean verifyIfIsOceanTech(String brand, String adTitle) {

		String brandLowerCase = brand.toLowerCase();
		String adTitleLowerCase = adTitle.toLowerCase();

		return isBrandEqualsOceanTech(brandLowerCase) || containsOceanInAdTitle(adTitleLowerCase);
	}

	private static Boolean isBrandEqualsOceanTech(String brandLowerCase) {
		return brandLowerCase.equals("ocean tech") || brandLowerCase.equals("oceantech");
	}

	public static Boolean containsOceanInAdTitle(String adTitleLowerCase) {
		return adTitleLowerCase.contains("ocean");
	}

}
