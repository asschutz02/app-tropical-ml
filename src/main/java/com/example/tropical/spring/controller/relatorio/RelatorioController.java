package com.example.tropical.spring.controller.relatorio;

import com.example.tropical.spring.service.relatório.RelatorioService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/tropical/relatorio")
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping()
    public void gerarRelatorio(){
        this.service.gerarRelatorio();
    }
}
