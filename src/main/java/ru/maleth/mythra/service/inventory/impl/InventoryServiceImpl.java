package ru.maleth.mythra.service.inventory.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.dto.mapper.ItemMapper;
import ru.maleth.mythra.enums.MasteryEnum;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.characters.Character;
import ru.maleth.mythra.model.characters.Mastery;
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
import java.util.Set;
import java.util.stream.Collectors;

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
    public void saveItem(Long charId, Long itemId) {
        log.info("Пришел запрос на сохранение предмета c id {} за персонажем с id {}", itemId, charId);
        Character character = characterRepo.findById(charId).orElseThrow(() -> new RuntimeException("А персонажа-то нет"));
        Item item = inventoryRepo.findById(itemId).orElseThrow(() -> new RuntimeException("Нет предмета"));
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
        Character character = characterRepo.findById(charId).orElseThrow(() -> new RuntimeException("А персонажа-то нет"));
        Set<MasteryEnum> masterySet = character.getMasteries().stream().map(Mastery::getName).collect(Collectors.toSet());
        List<ItemDTO> itemDTOList = new ArrayList<>();
        List<ItemDTO> itemDTOMelee = charInventoryRepo.findAllByCharacter_IdAndItem_Slot(charId, SlotEnum.MELEE_WEAPON).stream()
                .map(ci -> ItemMapper.fromItem(ci.getItem(), ci.getIsEquipped())).toList();
        List<ItemDTO> itemDTORanged = charInventoryRepo.findAllByCharacter_IdAndItem_Slot(charId, SlotEnum.RANGED_WEAPON).stream()
                .map(ci -> ItemMapper.fromItem(ci.getItem(), ci.getIsEquipped())).toList();
        for (ItemDTO i : itemDTOMelee) {
            i.setIsMasterful(masterySet.contains(i.getWeaponMastery()));
            itemDTOList.add(i);
        }
        for (ItemDTO i : itemDTORanged) {
            i.setIsMasterful(masterySet.contains(i.getWeaponMastery()));
            itemDTOList.add(i);
        }
        return gson.toJson(itemDTOList);
    }

    @Override
    public String findItems(String search) {
        log.info("Собираем все предметы по запросу '{}'", search);
        Set<ItemDTO> items = inventoryRepo.findIgnoreCaseByName(search).stream().map(i -> ItemMapper.fromItem(i, false)).collect(Collectors.toSet());
        for (ItemDTO i:
             items) {
            System.out.println(i);
        }
        log.info("Нашли {} записей", items.size());
        return gson.toJson(items);
    }
}
