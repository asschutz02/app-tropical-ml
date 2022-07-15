package com.example.tropical.selenium.excel.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.example.tropical.selenium.model.AdSalesMLResponse;

public class ExcelHelper {

	public static void populateExcel(XSSFSheet paginaDoExcel, AdSalesMLResponse adSalesMLResponse, String lojista,
			String vendedor) {
		List<String> titulos = new ArrayList<>();
		titulos.add("NOME DO PRODUTO");
		titulos.add("TÍTULO DO ANÚNCIO");
		titulos.add("PMS");
		titulos.add("DATA COLETA");
		titulos.add("LINK DO ANUNCIANTE");
		titulos.add("LINK DO ANÚNCIO");
		titulos.add("ID DO ANÚNCIO");
		titulos.add("VALOR ANUNCIADO");
		titulos.add("NICKNAME");
		titulos.add("LOJISTA");
		if (!Objects.isNull(vendedor)) {
			titulos.add("VENDEDOR");
		}

		int tamanhoDaPagina = paginaDoExcel.getPhysicalNumberOfRows();

		int numLinha;

		if (tamanhoDaPagina == 0) {
			numLinha = 0;
		} else {
			numLinha = tamanhoDaPagina + 1;
		}

		Row linha;

		if (tamanhoDaPagina == 0) {
			linha = paginaDoExcel.createRow(numLinha);
			numLinha++;

			for (int i = 0; i < titulos.size(); i++) {
				Cell celula = linha.createCell(i);
				celula.setCellValue(titulos.get(i));
			}
		}

		linha = paginaDoExcel.createRow(numLinha);

		Cell celula = linha.createCell(0);
		celula.setCellValue(adSalesMLResponse.getProductName().toUpperCase());

		celula = linha.createCell(1);
		celula.setCellValue(adSalesMLResponse.getAdTitle());

		celula = linha.createCell(2);
		celula.setCellValue(adSalesMLResponse.getPms());

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		celula = linha.createCell(3);
		celula.setCellValue(formatter.format(calendar.getTime()));

		celula = linha.createCell(4);
		celula.setCellValue(adSalesMLResponse.getLinkSeller());

		celula = linha.createCell(5);
		celula.setCellValue(adSalesMLResponse.getLinkAd());

		celula = linha.createCell(6);
		String linkAd = adSalesMLResponse.getLinkAd();
		String MLB = linkAd.substring(36);
		String idAD = MLB.substring(0, 14);
		celula.setCellValue(idAD);

		celula = linha.createCell(7);
		celula.setCellValue(adSalesMLResponse.getPrice());

		celula = linha.createCell(8);
		celula.setCellValue(adSalesMLResponse.getNickNameSeller().toUpperCase());

		celula = linha.createCell(9);
		if (Objects.isNull(lojista)) {
			celula.setCellValue(lojista);
		} else {
			celula.setCellValue(lojista.toUpperCase());
		}

		if (!Objects.isNull(vendedor)) {
			celula = linha.createCell(10);
			celula.setCellValue(vendedor);
		}
	}

	public static void validateNickname(AdSalesMLResponse adSalesMLResponse) {
		if (adSalesMLResponse.getNickNameSeller().contains("%C3%81") || adSalesMLResponse.getNickNameSeller().contains("%C3%82")
		|| adSalesMLResponse.getNickNameSeller().contains("%C3%89") || adSalesMLResponse.getNickNameSeller().contains("%C3%8A")
		|| adSalesMLResponse.getNickNameSeller().contains("%C3%83"))  {
			adSalesMLResponse.setNickNameSeller(adSalesMLResponse.getNickNameSeller().replace("%C3%81", "á"));
			adSalesMLResponse.setNickNameSeller(adSalesMLResponse.getNickNameSeller().replace("%C3%82", "â"));
			adSalesMLResponse.setNickNameSeller(adSalesMLResponse.getNickNameSeller().replace("%C3%89", "é"));
			adSalesMLResponse.setNickNameSeller(adSalesMLResponse.getNickNameSeller().replace("%C3%8A", "ê"));
			adSalesMLResponse.setNickNameSeller(adSalesMLResponse.getNickNameSeller().replace("%C3%83", "ã"));
		}
	}

	private static boolean startsWith(String precoAD) {
		return precoAD.startsWith("5") || precoAD.startsWith("6") || precoAD.startsWith("7") || precoAD.startsWith("8")
				|| precoAD.startsWith("9");
	}
}
