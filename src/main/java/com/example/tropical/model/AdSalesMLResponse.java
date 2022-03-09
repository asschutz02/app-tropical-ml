package com.example.tropical.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdSalesMLResponse {

    private String productName;
    private String linkSeller;
    private String linkAd;
    private LocalDate actualDate;
    private Double price;
    private String nickNameSeller;
    private String addressSeller;
}
