package com.example.tropical.spring.mercadolivre.mapper;

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
		adSalesMLResponse.setNickNameSeller(anuncio.getSeller().getNickname());
		adSalesMLResponse.setPms(productEntity.getPrice());

		return adSalesMLResponse;
	}
}
