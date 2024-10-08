package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.model.Character;

import java.util.Optional;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {

    Character findByCharName(String charName);

    Optional<Character> findByCreator_NameAndCharName(String userName, String charName);

    @Transactional
    @Modifying
    @Query("UPDATE Character c " +
            "SET c.experience = :experience " +
            "WHERE c.charName = :charName")
    void updateExp(String charName, Integer experience);

    @Transactional
    @Modifying
    @Query("UPDATE Character c " +
            "SET c.currentHP = :currentHP " +
            "WHERE c.charName = :charName")
    void updateHP(String charName, Integer currentHP);

}
