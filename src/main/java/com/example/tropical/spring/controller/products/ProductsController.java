package com.example.tropical.spring.controller.products;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.service.products.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tropical/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    @PostMapping
    public void insertProducts(@RequestBody ProductsEntity products){
        productsService.insertProduct(products);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<ProductsEntity> findAll(){
        return productsService.findAll();
    }

    @GetMapping("/{name}")
    public ProductsEntity findByName(@PathVariable String name){
        return productsService.findByName(name);
    }

    @PutMapping("/{name}")
    public void updateProduct(@RequestBody ProductsEntity products,
                              @PathVariable String name){
        productsService.updateProduct(products, name);
    }

    @DeleteMapping("/{name}")
    public void deleteName(@PathVariable String name){
        productsService.deleteProduct(name);
    }
}
