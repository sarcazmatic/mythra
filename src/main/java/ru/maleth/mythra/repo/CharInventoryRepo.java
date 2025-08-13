package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.items.CharacterItems;

import java.util.List;
import java.util.Optional;

public interface CharInventoryRepo extends JpaRepository<CharacterItems, Long> {

    List<CharacterItems> findAllByCharacter_IdOrderByIsEquippedDesc(Long charId);

    Optional<CharacterItems> findByCharacter_IdAndItem_Id(Long charId, Long itemId);

    List<CharacterItems> findAllByCharacter_IdAndItem_Slot(Long charId, SlotEnum slotEnum);


}
