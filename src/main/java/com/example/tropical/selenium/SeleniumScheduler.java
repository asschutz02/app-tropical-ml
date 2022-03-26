package com.example.tropical.selenium;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SeleniumScheduler {

    private static final String TIME_ZONE = "America/Sao_Paulo";
    private final SeleniumExecuter seleniumExecuter;

//    @Scheduled(cron = "0 0 0/6 ? * * *", zone = TIME_ZONE) //certo
//    @Scheduled(cron = "0 */5 * ? * *")
    public void executeApp(){
        this.seleniumExecuter.executeSelenium();
    }
}
