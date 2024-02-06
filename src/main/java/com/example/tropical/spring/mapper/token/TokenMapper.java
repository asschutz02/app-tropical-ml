package com.example.tropical.spring.mapper.token;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tropical.spring.entity.token.TokenEntity;
import com.example.tropical.spring.mercadolivre.model.token.TokenModelResponse;

@Mapper
public interface TokenMapper {

	@Update(value = " UPDATE tokens                                                  " +
			" SET    token=#{tokenModel.accessToken, jdbcType=VARCHAR},              " +
			"        refresh_token=#{tokenModel.refreshToken, jdbcType=VARCHAR},     " +
			" WHERE  token=#{token};                                                 " )
	void updateToken(@Param("tokenModel") TokenModelResponse tokenModel, String token);

	@Select("SELECT * FROM tokens")
	@Result(property = "refreshToken", column = "refresh_token")
	List<TokenEntity> findAll();
}
