package com.example.tropical.spring.service.search;

import static com.example.tropical.selenium.SeleniumExecuter.getProductsInfo;
import static com.example.tropical.selenium.SeleniumExecuter.linksPage;
import static com.example.tropical.selenium.SeleniumExecuter.searchProductByName;
import static com.example.tropical.selenium.email.EmailJavaSender.emailJavaSender;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tropical.selenium.excel.ExcelExecuter;
import com.example.tropical.selenium.model.AdSalesMLResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SearchService {

	private final ExcelExecuter excelExecuter;

	public void searchProduct(String productName, Double price) throws IOException {

		String firstPage = searchProductByName(productName);

		List<String> links = linksPage(firstPage, price);

		List<AdSalesMLResponse> relatorio = getProductsInfo(links, price, productName, null);

		List<AdSalesMLResponse> relatorioSemLinkNulo = relatorio.stream()
				.filter(rel -> !Objects.isNull(rel.getLinkAd())).collect(Collectors.toList());

		List<AdSalesMLResponse> relatorioFinal = relatorioSemLinkNulo.stream().filter(rel -> !rel.getLinkAd().contains("click1"))
				.collect(Collectors.toList());

		System.out.println("relatório: " + relatorioFinal);
		System.out.println("tamanho relatório: " + relatorioFinal.size());

		excelExecuter.createExcel(relatorioFinal);

		//        emailSender();
		emailJavaSender();
	}
}
