package com.example.tropical.spring.mercadolivre.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoLivreSeller {

	private String nickname;

	@JsonProperty("permalink")
	private String linkDoAnunciante;
}
