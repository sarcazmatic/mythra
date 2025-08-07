package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.enums.CustomEditEnum;
import ru.maleth.mythra.model.CharCustomEdits;

import java.util.Set;

public interface CustomEditsRepo extends JpaRepository<CharCustomEdits, Long> {

    Set<CharCustomEdits> findAllByCharacterId(Long charId);

    CharCustomEdits findByCharacterIdAndCustomEdits(Long charId, CustomEditEnum customeEdit);

}
