package com.example.tropical.spring.mercadolivre.mapper;

import static java.util.Objects.nonNull;

import java.util.List;

import com.example.tropical.selenium.model.AdSalesMLResponse;
import com.example.tropical.spring.entity.products.ProductsEntity;
import com.example.tropical.spring.mercadolivre.model.MercadoLivreModel;
import com.example.tropical.spring.mercadolivre.model.MercadoLivreResponse;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MercadoLivreMapper {

	public static AdSalesMLResponse mapperToRelatorioResponse(MercadoLivreResponse mercadoLivreResponse,
			MercadoLivreModel anuncio, ProductsEntity productEntity) {
		AdSalesMLResponse adSalesMLResponse = new AdSalesMLResponse();

		adSalesMLResponse.setProductName(mercadoLivreResponse.getProdutoPesquisado());
		adSalesMLResponse.setAdTitle(anuncio.getTituloDoAnuncio());
		adSalesMLResponse.setLinkSeller(anuncio.getSeller().getLinkDoAnunciante());
		adSalesMLResponse.setLinkAd(anuncio.getLinkDoAnuncio());
		adSalesMLResponse.setPrice(anuncio.getPrice().toString().replace(".", ","));
		if (nonNull(anuncio.getOriginalPrice())) {
			adSalesMLResponse.setOriginalPrice(anuncio.getOriginalPrice().toString().replace(".", ","));
		}
		adSalesMLResponse.setNickNameSeller(anuncio.getSeller().getNickname());
		adSalesMLResponse.setPms(productEntity.getPrice());
		adSalesMLResponse.setIdAd(getIdFromAd(anuncio.getLinkDoAnuncio()));

		return adSalesMLResponse;
	}

	private static String getIdFromAd(String linkDoAnuncio) {

		if (linkDoAnuncio.contains("/p/")) {
			List<String> linkSplitted = List.of(linkDoAnuncio.split("MLB"));

			String id = linkSplitted.get(1);

			return "MLB-".concat(id);
		} else {
			String MLB = linkDoAnuncio.substring(36);
			return MLB.substring(0, 14);
		}
	}
}
