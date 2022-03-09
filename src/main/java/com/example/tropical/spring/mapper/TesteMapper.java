package com.example.tropical.spring.mapper;

import com.example.tropical.spring.entity.TesteEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TesteMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert(value = "INSERT INTO testeTable (name, age)                          " +
            "        VALUES (#{testeEntity.name, jdbcType=VARCHAR},              " +
            "                #{testeEntity.age});                                " )
    void insert(@Param("testeEntity") TesteEntity testeEntity);
}
