package com.example.tropical.spring.service.search;

import com.example.tropical.selenium.excel.ExcelExecuter;
import com.example.tropical.selenium.model.AdSalesMLResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.tropical.selenium.SeleniumExecuter.*;
import static com.example.tropical.selenium.email.EmailJavaSender.emailJavaSender;

@Service
@AllArgsConstructor
public class SearchService {

    private final ExcelExecuter excelExecuter;

    public void searchProduct(String productName, Double price) throws IOException {

        String firstPage = searchProductByName(productName);

        List<String> links = linksPage(firstPage);

        List<AdSalesMLResponse> relatorio = getProductsInfo(links, price);

        System.out.println("relatório: " + relatorio);
        System.out.println("tamanho relatório: " + relatorio.size());

        excelExecuter.createExcel(relatorio);

//        emailSender();
        emailJavaSender();
    }
}
