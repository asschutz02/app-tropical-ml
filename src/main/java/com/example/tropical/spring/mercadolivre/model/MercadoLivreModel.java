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
public class MercadoLivreModel {

	private String id;

	@JsonProperty("title")
	private String tituloDoAnuncio;

	private Double price;

	private MercadoLivreSeller seller;

	@JsonProperty("permalink")
	private String linkDoAnuncio;

	@JsonProperty("attributes")
	private List<MercadoLivreAtributosDoAnuncio> atributosDoAnuncio;
}
