package com.example.tropical.spring.service.products;

import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mapper.products.ProductsMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsMapper productsMapper;

    public void insertProduct(ProductsEntity products){
        productsMapper.insertProduct(products);
    }

    public List<ProductsEntity> findAll(){
        return productsMapper.findAllProducts();
    }

    public ProductsEntity findByName(String name) {
        return productsMapper.findByName(name);
    }

    public void updateProduct(ProductsEntity products, String name){
        if(Objects.isNull(products.getPrice())){
            ProductsEntity product = findByName(name);
            products.setPrice(product.getPrice());
            productsMapper.updateProduct(products, name);
        } else if(Objects.isNull(products.getName())) {
            ProductsEntity product = findByName(name);
            products.setName(product.getName());
            productsMapper.updateProduct(products, name);
        } else {
            productsMapper.updateProduct(products, name);
        }
    }

    public void deleteProduct(String name){
        productsMapper.deleteProduct(name);
    }
}
