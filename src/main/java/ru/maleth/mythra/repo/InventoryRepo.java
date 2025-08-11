package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.items.Item;

public interface InventoryRepo extends JpaRepository<Item, Long> {

}
