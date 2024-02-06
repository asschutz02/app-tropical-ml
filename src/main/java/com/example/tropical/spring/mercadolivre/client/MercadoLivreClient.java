package com.example.tropical.spring.mercadolivre.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.tropical.spring.entity.token.TokenEntity;
import com.example.tropical.spring.mapper.token.TokenMapper;
import com.example.tropical.spring.mercadolivre.model.MercadoLivreResponse;
import com.example.tropical.spring.mercadolivre.model.token.TokenModelResponse;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MercadoLivreClient {

	private final TokenMapper tokenMapper;
	private final RestTemplate restTemplate;
	private static final String BASE_URL = "https://api.mercadolibre.com/sites/MLB/search";
	private static final String BASE_TOKEN = "Bearer ";
//	private static final String TOKEN = "APP_USR-6688446517137964-032112-f3aaa445c419c905f9d179b6ced95e91-1335584154";
	private static final String TOKEN = "APP_USR-7056842339787742-020518-f3dda08a81aaaf563f6665";
//	refreshToken = TG-65c15d1183edf800019d0d19-1335584154

	public MercadoLivreResponse searchProduct(String productName) {
		try {
			return searchProductInML(productName);
		} catch (HttpClientErrorException.Unauthorized e) {

			TokenModelResponse tokenModelResponse = getNewToken();

			TokenEntity tokenEntity = getTokenEntity();

			tokenMapper.updateToken(tokenModelResponse, tokenEntity.getToken());

			return searchProductInML(productName);
		}
	}

	private MercadoLivreResponse searchProductInML(String productName) {
		HttpHeaders headers = new HttpHeaders();
		TokenEntity tokenEntity = getTokenEntity();
		headers.add("Authorization", BASE_TOKEN.concat(tokenEntity.getToken()));
		HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

		return restTemplate.exchange(
				BASE_URL.concat("?q=").concat(productName).concat("&sort=price_asc"),
				HttpMethod.GET, httpEntity, MercadoLivreResponse.class
		).getBody();
	}

	private TokenModelResponse getNewToken() {
		System.out.println("GERANDO NOVO TOKEN");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		TokenEntity tokenEntity = getTokenEntity();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", System.getenv("grant_type"));
		map.add("client_id", System.getenv("client_id"));
		map.add("client_secret", System.getenv("client_secret"));
		map.add("refresh_token", tokenEntity.getRefreshToken());

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

		return restTemplate.exchange("https://api.mercadolibre.com/oauth/token",
						HttpMethod.POST,
						entity,
						TokenModelResponse.class)
				.getBody();
	}

	private TokenEntity getTokenEntity() {
		return tokenMapper.findAll().get(0);
	}
}
