package com.example.tropical.spring.mapper.lojistas;

import com.example.tropical.spring.entity.lojistas.LojistasEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LojistasMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert(value = "INSERT INTO lojistas (lojista)                           " +
            "        VALUES (#{lojistas.lojista, jdbcType=VARCHAR});          " )
    void insertLojista(@Param("lojistas") LojistasEntity lojistas);

    @Select("SELECT * FROM lojistas")
    List<LojistasEntity> findAll();

    @Delete("DELETE FROM lojistas WHERE lojista=#{lojista};")
    void deleteByLojista(String lojista);

    @Update(value = " UPDATE lojistas                                         " +
            " SET    lojista=#{lojistasEntity.lojista, jdbcType=VARCHAR}      " +
            " WHERE  lojista=#{lojista};                                      " )
    void updateLojista(@Param("lojistasEntity") LojistasEntity lojistasEntity, String lojista);
}
