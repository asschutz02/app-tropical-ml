package com.example.tropical.spring.mercadolivre.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.tropical.spring.mercadolivre.model.MercadoLivreResponse;

@Component
public class MercadoLivreClient {
	private static final String BASE_URL = "https://api.mercadolibre.com/sites/MLB/search";
	private static final String BASE_TOKEN = "Bearer ";
//	private static final String TOKEN = "APP_USR-6688446517137964-032112-f3aaa445c419c905f9d179b6ced95e91-1335584154";
	private static final String TOKEN = "APP_USR-7056842339787742-020209-49c0b6f6f810bc672426da6d23753250-1335584154";
//	refreshToken = TG-65bce79f8955fc00017fdc36-1335584154

	RestTemplate restTemplate = new RestTemplate();

	public MercadoLivreResponse searchProduct(String productName) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", BASE_TOKEN.concat(TOKEN));
		HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

		return restTemplate.exchange(
				BASE_URL.concat("?q=").concat(productName).concat("&sort=price_asc"),
				HttpMethod.GET, httpEntity, MercadoLivreResponse.class
		).getBody();
	}
}
