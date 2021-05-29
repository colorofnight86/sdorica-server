package com.example.sdorica.service.impl;

import com.example.sdorica.dao.CharacterMapper;
import com.example.sdorica.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public boolean addCharacter(Character character) {
        return characterMapper.insertSelective(character) > 0;
    }

    @Override
    public Character characterOfId(Integer id) {
        return characterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Character> allCharacter() {
        return characterMapper.allCharacter();
    }

    @Override
    public List<Character> characterOfName(String name) {
        return characterMapper.characterOfName(name);
    }

    @Override
    public List<Character> characterOfPosition(String position) {
        return characterMapper.characterOfPosition(position);
    }

    @Override
    public List<Character> characterOfSearch(String from, String keyword) {
        return characterMapper.characterOfSearch(from, keyword);
    }
}
