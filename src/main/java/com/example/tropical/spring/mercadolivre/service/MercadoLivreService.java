package com.example.tropical.spring.mercadolivre.service;

import static com.example.tropical.selenium.email.EmailJavaSender.emailJavaSender;
import static com.example.tropical.spring.mercadolivre.mapper.MercadoLivreMapper.mapperToRelatorioResponse;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tropical.selenium.excel.ExcelExecuter;
import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mercadolivre.client.MercadoLivreClient;
import com.example.tropical.spring.mercadolivre.model.MercadoLivreAtributosDoAnuncio;
import com.example.tropical.spring.mercadolivre.model.MercadoLivreModel;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MercadoLivreService {

	private final MercadoLivreClient client;
	private final ExcelExecuter excelExecuter;

	public void searchProduct(List<ProductsEntity> productsEntities) throws IOException {
		List<AdSalesMLResponse> relatorio = new ArrayList<>();

		productsEntities.forEach(product -> {
			System.out.println("product: " + product);
			var mercadoLivreResponse = client.searchProduct(product.getName());
			mercadoLivreResponse.getResults().forEach(anuncio -> {
				if (Boolean.TRUE.equals(isNotProdutoParecido(anuncio, product))) {
					if (Boolean.TRUE.equals(
							verifyIfIsOceanTech(anuncio.getAtributosDoAnuncio(), anuncio.getTituloDoAnuncio()))) {
						if (Boolean.TRUE.equals(validatePMS(anuncio.getPrice(), product.getPrice()))) {
							var relatorioResponse = mapperToRelatorioResponse(mercadoLivreResponse, anuncio,
									product);
							relatorio.add(relatorioResponse);
						}
					}
				}
			});
		});
		List<AdSalesMLResponse> relatorioFinal = filterOtherBrands(relatorio);
		excelExecuter.createExcel(relatorioFinal);
		emailJavaSender();
	}

	private Boolean isNotProdutoParecido(MercadoLivreModel mercadoLivreModel, ProductsEntity product) {
		Optional<String> produtoParecido = Optional.ofNullable(product.getProximo());
		var adTitle = mercadoLivreModel.getTituloDoAnuncio();

		if (produtoParecido.isPresent() && produtoParecido.get().contains(" ")) {
			String[] stringArray = produtoParecido.get().split(" ");
			List<String> nomesParecidos = List.of(stringArray);
			return !nomesParecidos.stream().anyMatch(adTitle::contains);
		} else if (produtoParecido.isPresent() && !produtoParecido.get().contains(" ")) {
			return !adTitle.contains(produtoParecido.get());
		} else {
			return produtoParecido.isEmpty();
		}
	}

	public static Boolean verifyIfIsOceanTech(List<MercadoLivreAtributosDoAnuncio> atributosDoAnuncio,
			String adTitle) {

		String brand = "";

		var brandFilterList = atributosDoAnuncio.stream()
				.filter(anuncio -> anuncio.getId().equals("BRAND"))
				.map(MercadoLivreAtributosDoAnuncio::getMarca)
				.collect(Collectors.toList());

		if (!brandFilterList.isEmpty() && nonNull(brandFilterList.get(0))) {
			brand = brandFilterList.get(0);
			String brandLowerCase = brand.toLowerCase();
			String adTitleLowerCase = adTitle.toLowerCase();

			return (isBrandEqualsOceanTech(brandLowerCase) || containsOceanInAdTitle(adTitleLowerCase))
					&& isNotAnotherBrand(adTitleLowerCase);
		} else {
			return false;
		}
	}

	private static Boolean isBrandEqualsOceanTech(String brandLowerCase) {
		return brandLowerCase.equals("ocean tech") || brandLowerCase.equals("oceantech");
	}

	public static Boolean containsOceanInAdTitle(String adTitleLowerCase) {
		return adTitleLowerCase.contains("ocean");
	}

	private static Boolean isNotAnotherBrand(String adTitleLowerCase) {
		return !adTitleLowerCase.contains("aqua ocean") && !adTitleLowerCase.contains("aquaocean") &&
				!adTitleLowerCase.contains("instant ocean") && !adTitleLowerCase.contains("instantocean")
				&& !adTitleLowerCase.contains("usado") && !adTitleLowerCase.contains("usada")
				&& !adTitleLowerCase.startsWith("controlador") && !adTitleLowerCase.contains("grade")
				&& !adTitleLowerCase.contains("impeller");
	}

	private Boolean validatePMS(Double adPrice, Double pms) {
		return isAdPriceBelowPMS(adPrice, pms) && isAdPriceHigherThanFiftyPercentOfPMS(adPrice, pms);
	}

	private Boolean isAdPriceBelowPMS(Double adPrice, Double pms) {
		return adPrice.intValue() < pms.intValue();
	}

	private Boolean isAdPriceHigherThanFiftyPercentOfPMS(Double adPrice, Double pms) {

		var halfPrice = pms.intValue() / 2;
		var quarterPrice = halfPrice / 2;

		var threeQuarterPrice = halfPrice + quarterPrice;

		return adPrice.intValue() > threeQuarterPrice;
	}

	private List<AdSalesMLResponse> filterOtherBrands(List<AdSalesMLResponse> relatorio) {
		return relatorio.stream().filter(MercadoLivreService::validateBrands).collect(Collectors.toList());
	}

	private static Boolean validateBrands(AdSalesMLResponse relatorioObject) {
		Optional<String> linkAd = Optional.ofNullable(relatorioObject.getLinkAd());
		return linkAd.isPresent();
	}
}
