package ru.maleth.mythra.service.inventory.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.dto.mapper.ItemMapper;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.characters.Character;
import ru.maleth.mythra.model.items.CharacterItems;
import ru.maleth.mythra.model.items.Item;
import ru.maleth.mythra.repo.CharInventoryRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.InventoryRepo;
import ru.maleth.mythra.service.inventory.InventoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final CharInventoryRepo charInventoryRepo;
    private final InventoryRepo inventoryRepo;
    private final CharacterRepo characterRepo;
    private final Gson gson;


    @Override
    public Map<String, String> addItemPageFormer(String userName, String charName) {
        log.info("Формируем страницу создания предмета для персонажа {}", charName);
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(() -> new RuntimeException("А персонажа-то нет"));
        Map<String, String> itemPageFormer = new HashMap<>();
        itemPageFormer.put("charId", String.valueOf(character.getId()));
        itemPageFormer.put("charName", charName);
        itemPageFormer.put("userName", userName);
        itemPageFormer.put("PAGE", "newitem");
        return itemPageFormer;
    }

    @Override
    public void saveItem(Long charId, ItemDTO itemDTO) {
        log.info("Пришел запрос на сохранение предмета {} за персонажем с id {}", itemDTO.getName(), charId);
        Character character = characterRepo.findById(charId).orElseThrow(() -> new RuntimeException("А персонажа-то нет"));
        Item item = ItemMapper.fromItemDto(itemDTO);
        System.out.println(item);
        inventoryRepo.save(item);
        log.info("Сохранение предмета успешно в репо предметов");
        CharacterItems characterItems = CharacterItems.builder()
                .item(item)
                .character(character)
                .numberOfUses(0)
                .isEquipped(false)
                .build();
        charInventoryRepo.save(characterItems);
        log.info("ID предмета закреплен за персонажем успешно в репо персонаж-предмет");
    }

    @Override
    public String loadCharacterItems(Long charId) {
        log.info("Пришел запрос на формирование JSON предметов персонажа с id {}", charId);
        List<CharacterItems> characterItems = charInventoryRepo.findAllByCharacter_IdOrderByIsEquippedDesc(charId);
        log.info("У персонажа с id {} найдено {} предметов", charId, characterItems.size());
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (CharacterItems ci :characterItems) {
            itemDTOList.add(ItemMapper.fromItem(ci.getItem(), ci.getIsEquipped()));
        }
        String response = gson.toJson(itemDTOList);
        return response;
    }

    @Override
    public String equipOrUnequip(Long charId, Long itemId) {
        log.info("Изменение состояния в связке персонаж/предмет {}/{}", charId, itemId);
        CharacterItems characterItem = charInventoryRepo.findByCharacter_IdAndItem_Id(charId, itemId).orElseThrow(() ->
                new RuntimeException("Не нашли связку предмет/персонаж"));
        characterItem.setIsEquipped(!characterItem.getIsEquipped());
        charInventoryRepo.save(characterItem);
        if (characterItem.getIsEquipped()) {
            return gson.toJson("Снять");
        } else {
            return gson.toJson("Надеть");
        }
    }

    @Override
    public void deleteItem(Long charId, Long itemId) {
        log.info("Удаляем связку персонаж/предмет {}/{}", charId, itemId);
        CharacterItems characterItem = charInventoryRepo.findByCharacter_IdAndItem_Id(charId, itemId).orElseThrow(() ->
                new RuntimeException("Не нашли связку предмет/персонаж"));
        charInventoryRepo.deleteById(characterItem.getId());
    }

    @Override
    public String putWeapons(Long charId) {
        log.info("Найдем оружие у персонажа {}", charId);
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<ItemDTO> itemDTOMelee = charInventoryRepo.findAllByCharacter_IdAndItem_Slot(charId, SlotEnum.MELEE_WEAPON).stream()
                .map(ci -> ItemMapper.fromItem(ci.getItem(), ci.getIsEquipped())).toList();
        List<ItemDTO> itemDTORanged = charInventoryRepo.findAllByCharacter_IdAndItem_Slot(charId, SlotEnum.RANGED_WEAPON).stream()
                .map(ci -> ItemMapper.fromItem(ci.getItem(), ci.getIsEquipped())).toList();
        itemDTOList.addAll(itemDTOMelee);
        itemDTOList.addAll(itemDTORanged);
        System.out.println(itemDTOList);
        return gson.toJson(itemDTOList);
    }
}
