package com.example.tropical.spring.service.products;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mapper.products.ProductsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsMapper productsMapper;

    public void insertProduct(ProductsEntity products){
        productsMapper.insertProduct(products);
    }

    public List<ProductsEntity> findAll(){
        return productsMapper.findAll();
    }

    public ProductsEntity findByName(String name) {
        return productsMapper.findByName(name);
    }

    public void updateProduct(ProductsEntity products, String name){
        productsMapper.updateProduct(products, name);
    }

    public void deleteProduct(String name){
        productsMapper.deleteProduct(name);
    }
}
