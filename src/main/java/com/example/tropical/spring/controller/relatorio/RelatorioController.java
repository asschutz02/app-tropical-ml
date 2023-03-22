package com.example.tropical.spring.controller.relatorio;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mercadolivre.service.MercadoLivreService;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/tropical/relatorio")
public class RelatorioController {

//    private final RelatorioService service;

    private final MercadoLivreService mercadoLivreService;

    @PatchMapping()
    public void gerarRelatorio(@RequestBody List<ProductsEntity> productsEntities) throws IOException {
        mercadoLivreService.searchProduct(productsEntities);
//        this.service.gerarRelatorio(productsEntities);
    }
}
