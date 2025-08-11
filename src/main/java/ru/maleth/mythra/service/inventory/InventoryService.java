package ru.maleth.mythra.service.inventory;

import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.model.items.CharacterItems;

import java.util.List;
import java.util.Map;

public interface InventoryService {


    Map<String, String> addItemPageFormer(String userName, String charName);

    void saveItem(Long charId, ItemDTO itemDTO);

    List<CharacterItems> getItems(Long charId);


}
