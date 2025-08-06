package ru.maleth.mythra.service.levelup.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.CharClassToLevelUpDTO;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.enums.ProfEnum;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.CustomEdits;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.ClassesRepo;
import ru.maleth.mythra.service.levelup.LevelUpService;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelUpServiceImpl implements LevelUpService {

    private final CharacterRepo characterRepo;
    private final CharClassLevelRepo charClassLevelRepo;
    private final ClassesRepo charClassRepo;
    private final Gson gson = new Gson();

    private static final String PAGE = "directToPage";

    @Override
    public Map<String, String> formLvlUpPage(String userName, String charName) {
        log.info("Создаем страницу для лвл-апа для персонажа '{}'", charName);
        Map<String, String> attributes = new HashMap<>();
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(()
                -> new RuntimeException("Не нашли персонажа в таблице Персонажи по имени " + charName));
        List<CharClassLevel> cclList = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        log.info("Находим все пары Персонаж-Класс для '{}'. Их {}", charName, cclList.size());
        List<CharClass> charClassListForMultiClass = charClassRepo.findAll();
        List<String> characterClassesWithLevels = new ArrayList<>();
        log.info("Находим все связки Персонаж-Класс-Уровень для определения имеющихся у персонажа '{}' классов на лвл-ап", charName);
        for (CharClassLevel charClassLevel : cclList) {
            String className = ClassEnum.valueOf(charClassLevel.getCharClass().getName()).getName();
            charClassListForMultiClass.remove(charClassLevel.getCharClass());
            characterClassesWithLevels.add(className);
            characterClassesWithLevels.add(String.valueOf(charClassLevel.getClassLevel()));
            log.info("Класс '{}' уже есть у персонажа '{}' на уровне {}. Добавляем в список лвл-апа, убираем из списка мультикласса", className, charName, charClassLevel.getClassLevel());
        }
        List<String> characterClassesForMultiClassNames = charClassListForMultiClass.stream().map(charClass -> ClassEnum.valueOf(charClass.getName()).getName()).toList();
        String numberOfClassesForMultiClass = String.valueOf(characterClassesForMultiClassNames.size());
        log.info("Формируем список классов для мультикласса. Имеем {} кандидатов", numberOfClassesForMultiClass);
        String characterClassesWithLevelsJson = gson.toJson(characterClassesWithLevels);
        log.info("Сформировали строчку Json с классами для лвл-апа на фронт: {}", characterClassesWithLevelsJson);
        String charClassListForMultiClassJson = gson.toJson(characterClassesForMultiClassNames);
        log.info("Сформировали строчку Json с классами для мультикласса на фронт: {}", charClassListForMultiClassJson);
        attributes.put("numberOfClassesForMultiClass", numberOfClassesForMultiClass);
        attributes.put("existingCharClasses", characterClassesWithLevelsJson);
        attributes.put("classesForMultiClass", charClassListForMultiClassJson);
        attributes.put("charName", charName);
        attributes.put("size", String.valueOf(characterClassesWithLevels.size()));
        attributes.put(PAGE, "levelup");
        log.info("Возвращаем атрибуты для страницы повышения уровня");
        return attributes;
    }

    @Override
    public Map<String, String> formRaiseAttributesPage(String userName, String charName) {
        log.info("Создаем страницу для повышения характеристик '{}'", charName);
        Map<String, String> attributes = new HashMap<>();
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(()
                -> new RuntimeException("Не нашли персонажа в таблице Персонажи по имени " + charName));
        log.info("Проверяем, готов ли персонаж '{}' к повышению навыков или выбору фита", charName);
        if (!character.getIsFeatOrStatsReady()) {
            log.info("Проверка провалена -- assert false = '{}'", character.getIsFeatOrStatsReady());
            return null;
        }
        log.info("Проверка пройдена -- assert true = '{}'", character.getIsFeatOrStatsReady());
        List<CharClassLevel> charClasses = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        StringBuilder charClassesStringBuilder = new StringBuilder();
        if (charClasses.size() > 1) {
            charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(0).getCharClass().getName()).getName());
            charClassesStringBuilder.append(" ");
            charClassesStringBuilder.append(charClasses.get(0).getClassLevel());
            for (int i = 1; i < charClasses.size(); i++) {
                charClassesStringBuilder.append(" / ");
                charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(i).getCharClass().getName()).getName());
                charClassesStringBuilder.append(" ");
                charClassesStringBuilder.append(charClasses.get(i).getClassLevel());
            }
        } else {
            charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(0).getCharClass().getName()).getName());
        }

        String charClassesString = charClassesStringBuilder.toString();

        attributes.put("strength", String.valueOf(character.getStrength()));
        attributes.put("dexterity", String.valueOf(character.getDexterity()));
        attributes.put("constitution", String.valueOf(character.getConstitution()));
        attributes.put("wisdom", String.valueOf(character.getWisdom()));
        attributes.put("intelligence", String.valueOf(character.getIntelligence()));
        attributes.put("charisma", String.valueOf(character.getCharisma()));
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceEnum().getName());
        attributes.put("charId", String.valueOf(character.getId()));
        attributes.put("charClass", charClassesString);
        attributes.put(PAGE, "raiseattributes");
        return attributes;
    }

    @Override
    public Map<String, String> formCompetencePage(String userName, String charName) {
        log.info("Создаем страницу для компетенция персонажа '{}'", charName);
        Map<String, String> attributes = new HashMap<>();
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(()
                -> new RuntimeException("Не нашли персонажа в таблице Персонажи по имени " + charName));
        List<String> proficienciesList = character.getProficiencies().stream().map(p -> ProfEnum.valueOf(p.getName()).getName()).toList();
        attributes.put("proficienciesList", gson.toJson(proficienciesList));
        attributes.put("proficiency", formatMods(CharacterCalculator.getProfBonus(character.getExperience())));
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceEnum().getName());
        attributes.put("charId", String.valueOf(character.getId()));
        attributes.put(PAGE, "competence");
        return attributes;
    }

    @Override
    public Map<String, String> formManualEdit(String userName, String charName) {
        log.info("Создаем страницу для ручной корректировки атрибутов и навыков персонажа '{}'", charName);
        Map<String, String> attributes = new HashMap<>();
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(()
                -> new RuntimeException("Не нашли персонажа в таблице Персонажи по имени " + charName));
        Map<String, Integer> characterCustomeEdits = character.getCustomEdits().stream().collect(Collectors.toMap(p -> p.getName().toString(), p -> p.getModificator()));
        attributes.put("attribStr", String.valueOf(character.getStrength()+characterCustomeEdits.get("STRENGTH")));
        attributes.put("attribDex", String.valueOf(character.getDexterity()+characterCustomeEdits.get("DEXTERITY")));
        attributes.put("attribCon", String.valueOf(character.getConstitution()+characterCustomeEdits.get("CONSTITUTION")));
        attributes.put("attribInt", String.valueOf(character.getIntelligence()+characterCustomeEdits.get("INTELLIGENCE")));
        attributes.put("attribWis", String.valueOf(character.getWisdom()+characterCustomeEdits.get("WISDOM")));
        attributes.put("attribCha", String.valueOf(character.getCharisma()+characterCustomeEdits.get("CHARISMA")));
        attributes.put(PAGE, "manual");
        return attributes;
    }

    @Override
    public void levelUp(CharClassToLevelUpDTO charClassToLevelUp) {
        Character character = characterRepo.findById(charClassToLevelUp.getCharId()).orElseThrow(()
                -> new RuntimeException("Не нашли записи в таблице Персонажи подходящей под условие фильтра id персонажа = " + charClassToLevelUp.getCharId()));
        log.info("Нашли персонажа с именем {} и id {} для повышения уровня", character.getCharName(), character.getId());
        CharClassLevel ccl = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId())
                .stream()
                .filter(c -> c.getCharClass().getName().equals(ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString()))
                .findFirst().orElseThrow(()
                        -> new RuntimeException("Не нашли записи в таблице Персонаж-Класс-Уровень подходящей под условие фильтра класс персонажа = " + charClassToLevelUp.getCharClassToLevelUp()));
        log.info("Нашли запись в таблице Персонаж-Класс-Уровень для персонажа {}, соответствующую классу отмеченному на повышение уровня {}",
                character.getCharName(),
                charClassToLevelUp.getCharClassToLevelUp());
        ccl.setClassLevel(ccl.getClassLevel() + 1);
        log.info("Изменяем поле 'уровень' класса персонажа '{}' в таблице Персонаж-Класс-Уровень c '{}' до '{}'", ccl.getCharClass().getName(), ccl.getClassLevel() - 1, ccl.getClassLevel());
        character.setIsLevelUpReady(false);
        log.info("Меняем запись в таблице персонажа '{}' isLevelUpReady на false (assert: {})", character.getCharName(), character.getIsLevelUpReady());
        if (ccl.getCharClass().getName().equals("WARRIOR")
                && (ccl.getClassLevel() % 4 == 0 && ccl.getClassLevel() != 20
                || ccl.getClassLevel() == 19
                || ccl.getClassLevel() == 6
                || ccl.getClassLevel() == 14)) {
            character.setIsFeatOrStatsReady(true);
            log.info("Проверяем на соответствие классу WARRIOR. Текущий класс -- '{}'. Проверяем уровень -- '{}'. При верном ставим готовность на true (assert '{}')", ccl.getCharClass().getName(), ccl.getClassLevel(), character.getIsFeatOrStatsReady());
        } else if (ccl.getClassLevel() % 4 == 0 && ccl.getClassLevel() != 20 || ccl.getClassLevel() == 19) {
            character.setIsFeatOrStatsReady(true);
            log.info("Проверяем на соответствие классу WARRIOR. Текущий класс -- '{}'. Проверяем уровень -- '{}'. При верном ставим готовность на true (assert '{}')", ccl.getCharClass().getName(), ccl.getClassLevel(), character.getIsFeatOrStatsReady());
        }
        characterRepo.save(character);
        charClassLevelRepo.save(ccl);
    }

    @Override
    public void multiClass(CharClassToLevelUpDTO charClassToLevelUp) {
        Character character = characterRepo.findById(charClassToLevelUp.getCharId()).orElseThrow(()
                -> new RuntimeException("Не нашли записи в таблице Персонажи подходящей под условие фильтра id персонажа = " + charClassToLevelUp.getCharId()));
        log.info("Нашли персонажа с именем {} и id {} для мультиклассирования в {}",
                character.getCharName(),
                character.getId(),
                ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString());
        CharClass charClass = charClassRepo.findByName(ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString());
        CharClassLevel charClassLevel = CharClassLevel.builder()
                .charClass(charClass)
                .character(character)
                .classLevel(1)
                .build();
        character.setIsLevelUpReady(false);
        charClassLevelRepo.save(charClassLevel);
    }

    private String formatMods(int mod) {
        if (mod > 0) {
            return ("+" + mod);
        }
        return String.valueOf(mod);
    }

}
