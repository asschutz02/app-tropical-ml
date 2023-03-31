package com.example.tropical.spring.mapper.nicknames;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;

@Mapper
public interface NicknamesMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert(value = "INSERT INTO nicknames (nickname, customer_by, lojista)   " +
            "        VALUES (#{nickname.nickname, jdbcType=VARCHAR},          " +
            "                #{nickname.customerBy, jdbcType=VARCHAR},        " +
            "                #{nickname.lojista, jdbcType=VARCHAR});          " )
    void insertAdvertiser(@Param("nickname") NicknamesEntity nickname);

    @Select("SELECT * FROM nicknames")
    @Result(property = "customerBy", column = "customer_by")
    List<NicknamesEntity> findaAll();

    @Select("SELECT * FROM nicknames WHERE nickname=#{nickname};")
    @Result(property = "customerBy", column = "customer_by")
    NicknamesEntity findByNickname(String nickname);

    @Update(value = " UPDATE nicknames                                         " +
            " SET    nickname=#{nick.nickname, jdbcType=VARCHAR},              " +
            "        customer_by=#{nick.customerBy, jdbcType=VARCHAR},         " +
            "        lojista=#{nick.lojista, jdbcType=VARCHAR}                 " +
            " WHERE  nickname=#{nickname};                                     " )
    void updateNickname(@Param("nick") NicknamesEntity nick, String nickname);

    @Update(value = " UPDATE nicknames                                         " +
            " SET    lojista=#{newLojista, jdbcType=VARCHAR}                   " +
            " WHERE  lojista=#{oldLojista};                                    " )
    void updateLojista(String newLojista, String oldLojista);

    @Delete("DELETE FROM nicknames WHERE nickname=#{nickname};")
    void deleteNickname(String nickname);
}
