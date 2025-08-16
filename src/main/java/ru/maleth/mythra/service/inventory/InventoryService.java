package ru.maleth.mythra.service.inventory;

import ru.maleth.mythra.dto.ItemDTO;

import java.util.Map;

public interface InventoryService {


    Map<String, String> addItemPageFormer(String userName, String charName);

    void saveItem(Long charId, ItemDTO itemDTO);

    void saveItem(Long charId, Long itemId);

    String loadCharacterItems(Long charId);

    String equipOrUnequip(Long charId, Long itemId);

    void deleteItem(Long charId, Long itemId);

    String putWeapons(Long charId);

    String findItems(String search);

}
