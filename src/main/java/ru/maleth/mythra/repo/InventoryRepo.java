package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.model.items.Item;

import java.util.Set;

public interface InventoryRepo extends JpaRepository<Item, Long> {

    @Query("SELECT i from Item i " +
            "WHERE ((:search IS NOT NULL) " +
            "AND UPPER(i.name) LIKE CONCAT ('%', UPPER(:search), '%'))")
    Set<Item> findIgnoreCaseByName(String search);

}
