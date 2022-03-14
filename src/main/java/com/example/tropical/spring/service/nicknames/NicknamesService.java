package com.example.tropical.spring.service.nicknames;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NicknamesService {

    private final NicknamesMapper nicknamesMapper;

    public void insertAdvertiser(NicknamesEntity advertisers){
        nicknamesMapper.insertAdvertiser(advertisers);
    }

    public List<NicknamesEntity> findAll(){
        return nicknamesMapper.findaAll();
    }

    public NicknamesEntity findByNickname(String nickname){
        return nicknamesMapper.findByNickname(nickname);
    }

    public void updateNickname(NicknamesEntity nicknamesEntity, String nickname){
        nicknamesMapper.updateNickname(nicknamesEntity, nickname);
    }

    public void deleteNickname(String nickname){
        nicknamesMapper.deleteNickname(nickname);
    }
}
