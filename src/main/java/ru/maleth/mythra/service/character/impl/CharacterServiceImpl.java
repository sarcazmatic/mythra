package ru.maleth.mythra.service.character.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.ProfEnum;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Proficiency;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepo characterRepo;
    private final Gson gson = new Gson();

    @Override
    public Character findByUserNameAndCharName(String userName, String charName) {
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).get();
        return character;
    }

    @Override
    public String loadAttrsAndSkills(Long charId) {
        log.info("Собираем атрибуты и навыки для персонажа с id {}", charId);
        Character character = characterRepo.findById(charId).get();
        Map<String, String> attrsAndSkills = new HashMap<>();
        Map<String, Integer> characterCustomeEdits = character.getCustomEdits().stream().collect(Collectors.toMap(p -> p.getName().toString(), p -> p.getModificator()));

        int strength = character.getStrength() + characterCustomeEdits.get("STRENGTH");
        int dexterity = character.getDexterity() + characterCustomeEdits.get("DEXTERITY");
        int constitution = character.getConstitution() + characterCustomeEdits.get("CONSTITUTION");
        int intelligence = character.getIntelligence() + characterCustomeEdits.get("INTELLIGENCE");
        int wisdom = character.getWisdom() + characterCustomeEdits.get("WISDOM");
        int charisma = character.getCharisma() + characterCustomeEdits.get("CHARISMA");

        Set<String> characterProficiencies = character.getProficiencies().stream().map(p
                -> p.getName().toUpperCase().replace('-', '_')).collect(Collectors.toSet());
        /*
        Собираем значения навыков и профишиенси, которые будем передавать в мапу
        allProficienciesList – список всех имеющихся профишиенси (специализаций в навыках) в формате SLEIGHT_OF_HAND.
        Чтобы сравнить со специализациями в списке characterProficiencies, содержимое characterProficiencies нужно
        перевести в формат NN_NN.
        Когда мы сравниваем, смотрим, присутствует ли специализация в списке специализаций персонажа – т.е.
        находим, в каких специализациях из всех профишиент наш персонаж. Если совпал, выцепляем эту специализацию
        из персонажа, чтобы увеличить показатель.
         */
        List<String> allProficienciesList = Arrays.stream(ProfEnum.values()).map(Enum::toString).toList();
        for (String s : allProficienciesList) {
            if (characterProficiencies.contains(s)) {
                /*
                Выцепив одну (например SLEIGHT_OF_HAND), добавляем к мод. атрибута бонус мастерства и суем в мапу на вывод.
                 */
                switch (s) {
                    case "ATHLETICS" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength)
                                    + CharacterCalculator.getProfBonus(character.getExperience()
                                    + characterCustomeEdits.get(s))));
                    case "ACROBATICS", "STEALTH", "SLEIGHT_OF_HAND" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)
                                    + CharacterCalculator.getProfBonus(character.getExperience()
                                    + characterCustomeEdits.get(s))));
                    case "ARCANA", "HISTORY", "INVESTIGATION", "NATURE", "RELIGION" ->
                            attrsAndSkills.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)
                                            + CharacterCalculator.getProfBonus(character.getExperience()
                                            + characterCustomeEdits.get(s))));
                    case "INSIGHT", "MEDICINE", "PERCEPTION", "SURVIVAL", "ANIMAL_HANDLING" ->
                            attrsAndSkills.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)
                                            + CharacterCalculator.getProfBonus(character.getExperience()
                                            + characterCustomeEdits.get(s))));
                    case "DECEPTION", "INTIMIDATION", "PERFORMANCE", "PERSUASION" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma)
                                    + CharacterCalculator.getProfBonus(character.getExperience()
                                    + characterCustomeEdits.get(s))));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            } else {
                /*
                Если навык из общего списка отсутствует в специализациях персонажа, ему присваивается базовое значение.
                И засовывается в мапу. Формат навыка, который отправляется в мапу в итоге получается sleight_of_hand.
                 */
                switch (s) {
                    case "ATHLETICS" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength)
                                    + characterCustomeEdits.get(s)));
                    case "ACROBATICS", "STEALTH", "SLEIGHT_OF_HAND" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)
                                    + characterCustomeEdits.get(s)));
                    case "ARCANA", "HISTORY", "INVESTIGATION", "NATURE", "RELIGION" ->
                            attrsAndSkills.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)
                                            + characterCustomeEdits.get(s)));
                    case "INSIGHT", "MEDICINE", "PERCEPTION", "SURVIVAL", "ANIMAL_HANDLING" ->
                            attrsAndSkills.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)
                                            + characterCustomeEdits.get(s)));
                    case "DECEPTION", "INTIMIDATION", "PERFORMANCE", "PERSUASION" -> attrsAndSkills.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma)
                                    + characterCustomeEdits.get(s)));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            }
        }

        List<AttribEnum> savingThrows = new ArrayList<>();
        savingThrows.add(character.getMainClass().getSavingThrowOne());
        savingThrows.add(character.getMainClass().getSavingThrowTwo());

        List<AttribEnum> allSavingThrows = Arrays.stream(AttribEnum.values()).toList();

        for (AttribEnum s : allSavingThrows) {
            if (savingThrows.contains(s)) {
                AttribEnum baseAttrib = AttribEnum.getAttribByName(s.getName());
                switch (baseAttrib.toString()) {
                    case "STRENGTH" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "DEXTERITY" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "CONSTITUTION" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(constitution) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "INTELLIGENCE" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(intelligence) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "WISDOM" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(wisdom) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "CHARISMA" -> attrsAndSkills.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma) + CharacterCalculator.getProfBonus(character.getExperience())));
                    default -> throw new RuntimeException("Не получилось проставить спасброски");
                }
            } else {
                /*
                Если навык из общего списка отсутствует в специализациях персонажа, ему присваивается базовое значение.
                И засовывается в мапу. Формат навыка, который отправляется в мапу в итоге получается sleight_of_hand.
                 */
                switch (s.toString()) {
                    case "STRENGTH" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
                    case "DEXTERITY" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
                    case "CONSTITUTION" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
                    case "INTELLIGENCE" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
                    case "WISDOM" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
                    case "CHARISMA" ->
                            attrsAndSkills.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
                    default -> throw new RuntimeException("Не получилось проставить спасброски");
                }
            }
        }

        attrsAndSkills.put("featReady", String.valueOf(character.getIsFeatOrStatsReady()));
        attrsAndSkills.put("initiative", formatMods(character.getInitiative()));
        attrsAndSkills.put("strength", String.valueOf(strength));
        attrsAndSkills.put("strengthmod", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
        attrsAndSkills.put("dexterity", String.valueOf(dexterity));
        attrsAndSkills.put("dexteritymod", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
        attrsAndSkills.put("constitution", String.valueOf(constitution));
        attrsAndSkills.put("constitutionmod", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
        attrsAndSkills.put("intelligence", String.valueOf(intelligence));
        attrsAndSkills.put("intelligencemod", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
        attrsAndSkills.put("wisdom", String.valueOf(wisdom));
        attrsAndSkills.put("wisdommod", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
        attrsAndSkills.put("charisma", String.valueOf(charisma));
        attrsAndSkills.put("charismamod", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
        String result = gson.toJson(attrsAndSkills);
        return result;
    }

    private String formatMods(int mod) {
        if (mod > 0) {
            return ("+" + mod);
        }
        return String.valueOf(mod);
    }

}
