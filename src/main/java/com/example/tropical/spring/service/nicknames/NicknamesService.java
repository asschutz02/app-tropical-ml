package com.example.tropical.spring.service.nicknames;

import com.example.tropical.spring.entity.nicknames.NicknamesEntity;
import com.example.tropical.spring.mapper.nicknames.NicknamesMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        if(Objects.isNull(nicknamesEntity.getNickname())){
            NicknamesEntity nick = findByNickname(nickname);
            nicknamesEntity.setNickname(nick.getNickname());
            nicknamesMapper.updateNickname(nicknamesEntity, nickname);
        } else if(Objects.isNull(nicknamesEntity.getCustomerBy())) {
            NicknamesEntity nick = findByNickname(nickname);
            nicknamesEntity.setCustomerBy(nick.getCustomerBy());
            nicknamesMapper.updateNickname(nicknamesEntity, nickname);
        } else if(Objects.isNull(nicknamesEntity.getLojista())){
            NicknamesEntity nick = findByNickname(nickname);
            nicknamesEntity.setLojista(nick.getLojista());
            nicknamesMapper.updateNickname(nicknamesEntity, nickname);
        } else {
            nicknamesMapper.updateNickname(nicknamesEntity, nickname);
        }
    }

    public void deleteNickname(String nickname){
        nicknamesMapper.deleteNickname(nickname);
    }
}
