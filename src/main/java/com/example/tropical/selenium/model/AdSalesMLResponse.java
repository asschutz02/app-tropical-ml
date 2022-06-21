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
    private Double price;
    private String nickNameSeller;
    private Double pms;
}
