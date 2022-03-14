package com.example.tropical.spring.service.seller;

import com.example.tropical.spring.entity.seller.SellerEntity;
import com.example.tropical.spring.mapper.seller.SellerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SellerService {

    private final SellerMapper sellerMapper;

    public void insertSeller(SellerEntity seller){
        sellerMapper.insertSeller(seller);
    }

    public List<SellerEntity> findAll() {
        return sellerMapper.findaAll();
    }

    public void deleteSeller(String name){
        sellerMapper.deleteByName(name);
    }

    public void updateSeller(SellerEntity seller, String name){
        sellerMapper.updateSeller(seller, name);
    }
}
