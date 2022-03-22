package com.example.tropical.spring.service.search;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.tropical.selenium.SeleniumExecuter.searchProductByName;

@Service
@AllArgsConstructor
public class SearchService {

    public void searchProduct(String productName){
        searchProductByName(productName);
    }
}
