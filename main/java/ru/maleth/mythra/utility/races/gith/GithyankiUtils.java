package ru.maleth.mythra.utility.races.gith;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.RaceEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GithyankiUtils {

    private final AbilityRepo abilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;

    public List<CharRaceAbility> formAbilities(Character character) {
        Integer level = CharacterCalculator.getLevel(character.getExperience());
        List<CharRaceAbility> craList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByRaceLimitByLevel(character.getCharRace().getRaceEnum(), level);
        for (Ability a : abilities) {
            Optional<CharRaceAbility> craOptional = Optional.ofNullable(charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharRaceAbility cra;
            if (craOptional.isEmpty()) {
                cra = CharRaceAbility.builder()
                        .ability(a)
                        .race(character.getCharRace())
                        .character(character)
                        .build();
                switch (cra.getAbility().getName()) {
                    case "ПСИОНИКА ГИТЪЯНКИ" -> cra.setNumberOfUses(1);
                    default -> cra.setNumberOfUses(0);
                }
                charRaceAbilityRepo.save(cra);
            } else {
                cra = craOptional.get();
            }
            craList.add(cra);
        }
        return craList;
    }

}
