package com.example.tropical.selenium.excel.helper;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static void populateExcel(XSSFSheet paginaDoExcel, AdSalesMLResponse adSalesMLResponse){
        List<String> titulos = new ArrayList<>();
        titulos.add("PRODUTO");
        titulos.add("LINK");
        titulos.add("VALOR ANÚNCIO");
        titulos.add("NICKNAME");
        titulos.add("LINK ANUNCIANTE");
        titulos.add("LOJA");

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
        celula.setCellValue(adSalesMLResponse.getProductName());

        celula = linha.createCell(1);
        celula.setCellValue(adSalesMLResponse.getLinkAd());

        celula = linha.createCell(2);
        celula.setCellValue(adSalesMLResponse.getPrice());

        celula = linha.createCell(3);
        celula.setCellValue(adSalesMLResponse.getNickNameSeller());

        celula = linha.createCell(4);
        celula.setCellValue(adSalesMLResponse.getLinkSeller());
    }
}
