package com.example.tropical.spring.controller.lojistas;

import com.example.tropical.spring.entity.lojistas.LojistasEntity;
import com.example.tropical.spring.service.lojistas.LojistaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/tropical/lojista")
@RestController
@AllArgsConstructor
public class LojistaController {

    private final LojistaService lojistaService;

    @PostMapping()
    public void insertLojista(@RequestBody LojistasEntity lojistas){
        lojistaService.insertLojista(lojistas);
    }

    @GetMapping("/all")
    public List<LojistasEntity> findAll(){
        return lojistaService.findAll();
    }

    @DeleteMapping("/{lojista}")
    public void deleteLojista(@PathVariable String lojista){
        lojistaService.deleteLojista(lojista);
    }

    @PutMapping("/{lojista}")
    public void updateLojista(@RequestBody LojistasEntity lojistas, @PathVariable String lojista){
        lojistaService.updateLojista(lojistas, lojista);
    }
}
