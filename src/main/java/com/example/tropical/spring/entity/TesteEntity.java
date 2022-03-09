package com.example.tropical.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TesteEntity {

    private String name;
    private Integer id;
    private Integer age;
}
