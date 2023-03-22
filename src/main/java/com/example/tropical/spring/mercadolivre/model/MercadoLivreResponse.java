package com.example.tropical.spring.mercadolivre.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoLivreResponse {

	@JsonProperty("query")
	private String produtoPesquisado;
	private List<MercadoLivreModel> results;
}
