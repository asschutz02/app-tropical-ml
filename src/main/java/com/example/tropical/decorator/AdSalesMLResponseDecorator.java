package com.example.tropical.decorator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdSalesMLResponseDecorator {

    public static String getNickName(String linkSeller){
        String fullLink = "https://www.mercadolivre.com.br/perfil/";
        Integer lenght = fullLink.length();
        System.out.println("tamanho do link: " + lenght );

        String nickNameWithoutLink = linkSeller.substring(lenght - 4);

        return nickNameWithoutLink.replace("+", " ");
    }
}
