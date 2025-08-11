package ru.maleth.mythra.service.inventory.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.dto.mapper.ItemMapper;
import ru.maleth.mythra.model.characters.Character;
import ru.maleth.mythra.model.items.CharacterItems;
import ru.maleth.mythra.model.items.Item;
import ru.maleth.mythra.repo.CharInventoryRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.InventoryRepo;
import ru.maleth.mythra.service.inventory.InventoryService;

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
                .build();
        charInventoryRepo.save(characterItems);
        log.info("ID предмета закреплен за персонажем успешно в репо персонаж-предмет");
    }

    public List<CharacterItems> getItems(Long charId) {
        return charInventoryRepo.findAllByCharacter_Id(charId);
    }
}
