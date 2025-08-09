package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.model.abilities.CharRaceAbility;
import ru.maleth.mythra.model.characters.Race;



public interface CharRaceAbilityRepo extends JpaRepository<CharRaceAbility, Long> {

    CharRaceAbility findByCharacter_IdAndAbility_Name(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE CharRaceAbility cra " +
            "SET cra.numberOfUses = :charges " +
            "WHERE cra.ability.name = :ability " +
            "AND cra.character.charName = :name " +
            "AND cra.race = :race")
    void updateCharges(String ability, String name, Race race, Integer charges);

}
