package com.example.tropical.spring.entity.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsEntity {

    private Integer id;
    private String name;
    private Double price;
}
