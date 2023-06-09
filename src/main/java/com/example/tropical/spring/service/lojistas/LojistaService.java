package com.example.tropical.spring.service.lojistas;

import com.example.tropical.spring.entity.lojistas.LojistasEntity;
import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.mapper.lojistas.LojistasMapper;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LojistaService {

    private final LojistasMapper lojistasMapper;
    private final NicknamesMapper nicknamesMapper;

    public void insertLojista(LojistasEntity lojistas){
        lojistasMapper.insertLojista(lojistas);
    }

    public List<LojistasEntity> findAll() {
        return lojistasMapper.findAll();
    }

    public void deleteLojista(String lojista){
        List<NicknamesEntity> nicknamesRelateToLojista = nicknamesMapper.findByLojista(lojista);

        if (!nicknamesRelateToLojista.isEmpty()) {
            throw new RuntimeException();
        }
        lojistasMapper.deleteByLojista(lojista);
    }

    public void updateLojista(LojistasEntity lojistas, String lojista){
        lojistasMapper.updateLojista(lojistas, lojista);
        nicknamesMapper.updateLojista(lojistas.getLojista(), lojista);
    }
}
