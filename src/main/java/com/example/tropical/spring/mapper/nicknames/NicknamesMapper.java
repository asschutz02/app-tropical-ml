package com.example.tropical.spring.mapper.nicknames;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Delete("DELETE FROM nicknames WHERE nickname=#{nickname};")
    void deleteNickname(String nickname);
}
