package com.example.tropical.spring.service.relatório;

import com.example.tropical.selenium.SeleniumExecuter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RelatorioService {

    private final SeleniumExecuter executer;

    public void gerarRelatorio(){
        this.executer.executeSelenium();
    }
}
