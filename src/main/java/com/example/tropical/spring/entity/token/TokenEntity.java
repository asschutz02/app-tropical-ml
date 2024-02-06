package com.example.tropical.spring.entity.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenEntity {

	private Integer id;
	private String token;
	private String refreshToken;
}
