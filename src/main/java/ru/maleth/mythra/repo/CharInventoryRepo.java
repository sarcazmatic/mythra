package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.items.CharacterItems;

import java.util.List;

public interface CharInventoryRepo extends JpaRepository<CharacterItems, Long> {

    List<CharacterItems> findAllByCharacter_Id(Long charId);

}
