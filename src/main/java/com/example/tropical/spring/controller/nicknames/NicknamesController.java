package com.example.tropical.spring.controller.nicknames;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.service.nicknames.NicknamesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tropical/nicknames")
@AllArgsConstructor
public class NicknamesController {

    private final NicknamesService nicknamesService;

    @PostMapping()
    public void insertAdvertiser(@RequestBody NicknamesEntity advertisers){
        nicknamesService.insertAdvertiser(advertisers);
    }

    @GetMapping("/all")
    public List<NicknamesEntity> findAll(){
        return nicknamesService.findAll();
    }

    @GetMapping("/{nickname}")
    public NicknamesEntity findByNickname(@PathVariable String nickname){
        return nicknamesService.findByNickname(nickname);
    }

    @PutMapping("/{nickname}")
    public void updateNickname(@RequestBody NicknamesEntity nicknamesEntity,
                               @PathVariable String nickname){
        nicknamesService.updateNickname(nicknamesEntity, nickname);
    }

    @DeleteMapping("/{nickname}")
    public void deleteNickname(@PathVariable String nickname){
        nicknamesService.deleteNickname(nickname);
    }
}
