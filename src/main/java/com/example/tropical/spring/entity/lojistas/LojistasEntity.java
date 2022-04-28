package com.example.tropical.spring.entity.lojistas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LojistasEntity {

    private Integer id;
    private String lojista;
}
