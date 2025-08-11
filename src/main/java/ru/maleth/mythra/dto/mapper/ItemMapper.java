package ru.maleth.mythra.dto.mapper;

import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.items.Armor;
import ru.maleth.mythra.model.items.Item;
import ru.maleth.mythra.model.items.Weapon;

public class ItemMapper {

    public static Item fromItemDto(ItemDTO itemDTO) {
        Item item = Item.builder()
                .name(itemDTO.getName())
                .description(itemDTO.getDescription())
                .weight(itemDTO.getWeight())
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
        if (itemDTO.getType().equals(SlotEnum.MELEE_WEAPON)) {
            Weapon weapon = Weapon.builder()
                    .baseModificator(AttribEnum.STRENGTH)
                    .isFinesse(itemDTO.getIsFinesse())
                    .isUniversal(itemDTO.getIsUniversal())
                    .numberOfDice(itemDTO.getNumberOfDice())
                    .qualityOfDice(itemDTO.getQualityOfDice())
                    .build();
            item.setWeapon(weapon);
        } else if (itemDTO.getType().equals(SlotEnum.RANGED_WEAPON)) {
            Weapon weapon = Weapon.builder()
                    .baseModificator(AttribEnum.DEXTERITY)
                    .isFinesse(itemDTO.getIsFinesse())
                    .isUniversal(itemDTO.getIsUniversal())
                    .numberOfDice(itemDTO.getNumberOfDice())
                    .qualityOfDice(itemDTO.getQualityOfDice())
                    .build();
            item.setWeapon(weapon);
        }
        return item;
    }
}
