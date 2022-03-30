package com.example.tropical.spring.controller.search;

import com.example.tropical.spring.service.search.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/tropical/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService service;

    @GetMapping("/{productName}/price/{price}")
    public void searchProduct(@PathVariable String productName, @PathVariable Double price) throws IOException {
        service.searchProduct(productName, price);
    }
}
