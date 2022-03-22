package com.example.tropical.spring.controller.search;

import com.example.tropical.spring.service.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tropical/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService service;

    @GetMapping("/{productName}")
    public void searchProduct(@PathVariable String productName){
        service.searchProduct(productName);
    }
}
