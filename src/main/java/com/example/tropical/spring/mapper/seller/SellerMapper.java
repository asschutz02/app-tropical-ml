package com.example.tropical.spring.mapper.seller;

import com.example.tropical.spring.entity.seller.SellerEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellerMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert(value = "INSERT INTO sellers (name)                             " +
            "        VALUES (#{seller.name, jdbcType=VARCHAR});             " )
    void insertSeller(@Param("seller") SellerEntity seller);

    @Select("SELECT * FROM sellers")
    List<SellerEntity> findAll();

    @Delete("DELETE FROM sellers WHERE name=#{name};")
    void deleteByName(String name);

    @Update(value = " UPDATE sellers                                         " +
            " SET    name=#{seller.name, jdbcType=VARCHAR}                   " +
            " WHERE  name=#{name};                                           " )
    void updateSeller(@Param("seller") SellerEntity seller, String name);
}
