package com.example.tropical.selenium.excel.helper;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExcelHelper {

    public static void populateExcel(XSSFSheet paginaDoExcel, AdSalesMLResponse adSalesMLResponse, String lojista,
            String vendedor){
        List<String> titulos = new ArrayList<>();
        titulos.add("NOME DO PRODUTO");
        titulos.add("TÍTULO DO ANÚNCIO");
        titulos.add("PMS");
        titulos.add("LINK DO ANÚNCIO");
        titulos.add("ID DO ANÚNCIO");
        titulos.add("VALOR ANUNCIADO");
        titulos.add("NICKNAME");
        titulos.add("LOJISTA");
        titulos.add("LINK DO ANUNCIANTE");
        if (!Objects.isNull(vendedor)) {
            titulos.add("VENDEDOR");
        }


        int tamanhoDaPagina = paginaDoExcel.getPhysicalNumberOfRows();

        int numLinha;

        if(tamanhoDaPagina == 0 ){
            numLinha = 0;
        } else {
            numLinha = tamanhoDaPagina + 1;
        }

        Row linha;

        if(tamanhoDaPagina == 0 ) {
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

        celula = linha.createCell(3);
        celula.setCellValue(adSalesMLResponse.getLinkAd());

        celula = linha.createCell(4);
        String linkAd = adSalesMLResponse.getLinkAd();
        String MLB = linkAd.substring(36);
        String idAD = MLB.substring(0,14);
        celula.setCellValue(idAD);

        celula = linha.createCell(5);
        String precoAD = adSalesMLResponse.getPrice().toString();
        String pms = adSalesMLResponse.getPms().toString();
        if (precoAD.length() == 4 && (precoAD.contains(",") || precoAD.contains("."))) {
            if (pms.length() == 6) {
                String ponto = precoAD.replace(",", ".");
                celula.setCellValue(ponto.concat("0"));
            } else if (pms.length() == 5) {
                String ponto = precoAD.replace(",", ".");
                celula.setCellValue(ponto.concat("0"));
            } else if (pms.length() == 4) {
                celula.setCellValue(adSalesMLResponse.getPrice());
            }
        } else {
            celula.setCellValue(adSalesMLResponse.getPrice());
        }

        celula = linha.createCell(6);
        celula.setCellValue(adSalesMLResponse.getNickNameSeller().toUpperCase());

        celula = linha.createCell(7);
        if (Objects.isNull(lojista)) {
            celula.setCellValue(lojista);
        } else {
            celula.setCellValue(lojista.toUpperCase());
        }

        celula = linha.createCell(8);
        celula.setCellValue(adSalesMLResponse.getLinkSeller());

        if (!Objects.isNull(vendedor)) {
            celula = linha.createCell(9);
            celula.setCellValue(vendedor);
        }
    }
}
