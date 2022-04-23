package com.example.tropical.spring.controller.products.email;

import com.example.tropical.spring.service.products.email.ProductsEmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/tropical/products/email")
@AllArgsConstructor
public class ProductEmailController {

    private final ProductsEmailService productsEmailService;

    @GetMapping()
    public void productEmailRelatorio() {
        productsEmailService.productEmailRelatorio();
    }
}
