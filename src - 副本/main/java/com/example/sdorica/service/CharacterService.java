package com.example.sdorica.service;

import java.util.List;

public interface CharacterService {

    boolean addCharacter (Character character);

//    boolean deleteCharacter (Integer id);

    Character characterOfId(Integer id);

    List<Character> allCharacter();

    List<Character> characterOfName(String name);

    List<Character> characterOfPosition(String position);

    List<Character> characterOfSearch(String from, String keyword);
}
