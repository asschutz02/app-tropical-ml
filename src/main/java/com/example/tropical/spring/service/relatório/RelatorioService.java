package com.example.tropical.spring.service.relatório;

import java.util.List;

import com.example.tropical.selenium.SeleniumExecuter;
import com.example.tropical.spring.entity.products.ProductsEntity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RelatorioService {

    private final SeleniumExecuter executer;

    public void gerarRelatorio(List<ProductsEntity> productsEntities){
        this.executer.executeSelenium(productsEntities);
    }
}
