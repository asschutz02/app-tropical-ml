package com.example.tropical.selenium.decorator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdSalesMLResponseDecorator {

    public static String getNickName(String linkSeller){
        String fullLink = "https://www.mercadolivre.com.br/perfil/";
        int lenght = fullLink.length();

        String nickNameWithoutLink = linkSeller.substring(lenght - 4);

        return nickNameWithoutLink.replace("+", " ");
    }
}
