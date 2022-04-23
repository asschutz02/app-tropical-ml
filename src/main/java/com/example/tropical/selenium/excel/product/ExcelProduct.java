package com.example.tropical.selenium.excel.product;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mapper.products.ProductsMapper;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ExcelProduct {

    private final ProductsMapper productsMapper;

    public void createExcelProduct() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        List<ProductsEntity> allProducts = productsMapper.findAllProducts();

        XSSFSheet productsSheet = workbook.createSheet("PRODUTOS");

        allProducts.forEach(prod -> populateExcelNickname(productsSheet, prod));

        FileOutputStream out = null;
        try {
            out = new FileOutputStream("produtos-tropical-ml.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert out != null;
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void populateExcelNickname(XSSFSheet paginaDoExcel, ProductsEntity obj){
        List<String> titulos = new ArrayList<>();
        titulos.add("NOME");
        titulos.add("PMS");

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
        celula.setCellValue(obj.getName());

        celula = linha.createCell(1);
        celula.setCellValue(obj.getPrice());
    }
}
