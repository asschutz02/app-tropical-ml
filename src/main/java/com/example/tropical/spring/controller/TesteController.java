package com.example.tropical.spring.controller;

import com.example.tropical.spring.entity.TesteEntity;
import com.example.tropical.spring.mapper.TesteMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {

    private final TesteMapper testeMapper;

    @PostMapping()
    void insert(@RequestBody TesteEntity testeEntity) {
        testeMapper.insert(testeEntity);
    }
}
