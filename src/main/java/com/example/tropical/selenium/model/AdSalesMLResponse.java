package com.example.tropical.selenium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdSalesMLResponse {

    private String productName;
    private String adTitle;
    private String linkSeller;
    private String linkAd;
    private String price;
    private String originalPrice;
    private String nickNameSeller;
    private Double pms;
    private String idAd;
}
