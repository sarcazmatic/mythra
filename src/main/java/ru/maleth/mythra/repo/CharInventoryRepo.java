package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.items.CharacterItems;

public interface CharInventoryRepo extends JpaRepository<CharacterItems, Long> {
}
