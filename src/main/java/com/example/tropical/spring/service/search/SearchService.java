package com.example.tropical.spring.service.search;

import com.example.tropical.selenium.excel.ExcelExecuter;
import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.service.products.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.example.tropical.selenium.SeleniumExecuter.*;
import static com.example.tropical.selenium.email.EmailSender.emailSender;

@Service
@AllArgsConstructor
public class SearchService {

    private final ExcelExecuter excelExecuter;
    private final ProductsService productsService;

    public void searchProduct(String productName) throws IOException {

        String firstPage = searchProductByName(productName);

        List<String> links = linksPage(firstPage);

        ProductsEntity products = productsService.findByName(productName);

        List<AdSalesMLResponse> relatorio = getProductsInfo(links, products);

        System.out.println("relatório: " + relatorio);

        excelExecuter.createExcel(relatorio);

        emailSender();
    }
}
