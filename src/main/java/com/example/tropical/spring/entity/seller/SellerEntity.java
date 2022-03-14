package com.example.tropical.spring.entity.seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SellerEntity {

    private Integer id;
    private String name;
}
