package ru.maleth.mythra.service.character;

import ru.maleth.mythra.model.Character;

import java.util.Map;

public interface CharacterService {

    Character findByUserNameAndCharName(String userName, String charName);

    String loadAttrsAndSkills(Long charId);

    void manualEdit(Long charId, Map<String, Integer> manualEdits);


}
