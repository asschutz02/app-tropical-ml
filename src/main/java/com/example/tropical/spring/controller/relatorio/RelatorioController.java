package com.example.tropical.spring.controller.relatorio;

import java.util.List;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.service.relatório.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/tropical/relatorio")
public class RelatorioController {

    private final RelatorioService service;

    @PatchMapping()
    @CrossOrigin(origins = "https://tropical-ml-frontend.herokuapp.com/")
    public void gerarRelatorio(@RequestBody List<ProductsEntity> productsEntities){
        this.service.gerarRelatorio(productsEntities);
    }
}
