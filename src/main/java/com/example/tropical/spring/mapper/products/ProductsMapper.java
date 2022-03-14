package com.example.tropical.spring.mapper.products;

import com.example.tropical.spring.entity.products.ProductsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductsMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert(value = "INSERT INTO products (name, price)                   " +
            "        VALUES (#{products.name, jdbcType=VARCHAR},          " +
            "                #{products.price, jdbcType=NUMERIC});        " )
    void insertProduct(@Param("products") ProductsEntity products);

    @Select("SELECT * FROM products")
    List<ProductsEntity> findAll();

    @Select("SELECT * FROM products WHERE name=#{name};")
    ProductsEntity findByName(String name);

    @Update(value = " UPDATE products                                      " +
            " SET    name=#{products.name, jdbcType=VARCHAR},              " +
            "        price=#{products.price, jdbcType=NUMERIC}             " +
            " WHERE  name=#{name};                                         " )
    void updateProduct(@Param("products") ProductsEntity products, String name);

    @Delete("DELETE FROM products WHERE name=#{name};")
    void deleteProduct(String name);
}
