package ru.maleth.mythra.dto.mapper;

import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.enums.ArmorTypeEnum;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.items.Armor;
import ru.maleth.mythra.model.items.Item;
import ru.maleth.mythra.model.items.Weapon;

import java.util.Optional;

public class ItemMapper {

    public static Item fromItemDto(ItemDTO itemDTO) {
        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .weight((double) itemDTO.getWeight())
                .slot(itemDTO.getType())
                .build();
        if (itemDTO.getArmorType() != null) {
            Armor armor = Armor.builder()
                    .armorType(itemDTO.getArmorType())
                    .ac(10)
                    .stealthDisadvantage(true)
                    .build();
            item.setArmor(armor);
        }
        if (itemDTO.getType().equals(SlotEnum.MELEE_WEAPON) || itemDTO.getType().equals(SlotEnum.RANGED_WEAPON)) {
            Weapon weapon = Weapon.builder()
                    .baseModificator(1)
                    .isFinesse(true)
                    .isUniversal(true)
                    .numberOfDice(1)
                    .qualityOfDice(8)
                    .build();
            item.setWeapon(weapon);
        }
        return item;
    }
}
