package com.example.sdorica.service;
import com.example.sdorica.domain.Character;
import java.util.List;

public interface CharacterService {

    boolean addCharacter (Character character);

    boolean updateCharacter (Character character);

//    boolean deleteCharacter (Integer id);

    Character characterOfId(Integer id);

    List<Character> allCharacter();

    List<Character> characterOfName(String name);

    List<Character> characterOfPosition(String position);

    List<Character> characterOfSearch(String from, String keyword);

    List<Character> characterOfFilter(String have, String position, String number, String type);
}
