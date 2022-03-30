package com.example.tropical.spring.controller.seller;

import com.example.tropical.spring.entity.seller.SellerEntity;
import com.example.tropical.spring.service.seller.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/tropical/sellers")
@RestController
@AllArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping()
    public void insertSeller(@RequestBody SellerEntity seller){
        sellerService.insertSeller(seller);
    }

    @GetMapping("/all")
    public List<SellerEntity> findAll(){
        return sellerService.findAll();
    }

    @DeleteMapping("/{name}")
    public void deleteSeller(@PathVariable String name){
        sellerService.deleteSeller(name);
    }

    @PutMapping("/{name}")
    public void updateSeller(@RequestBody SellerEntity seller, @PathVariable String name){
        sellerService.updateSeller(seller, name);
    }
}
