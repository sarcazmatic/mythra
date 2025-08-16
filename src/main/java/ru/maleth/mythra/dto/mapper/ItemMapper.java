package ru.maleth.mythra.dto.mapper;

import ru.maleth.mythra.dto.ItemDTO;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.MasteryEnum;
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
                .slot(SlotEnum.valueOf(itemDTO.getType()))
                .build();
        if (itemDTO.getArmorType() != null) {
            Armor armor = Armor.builder()
                    .armorType(itemDTO.getArmorType())
                    .ac(10)
                    .stealthDisadvantage(true)
                    .build();
            item.setArmor(armor);
        }
        if (itemDTO.getType().equals(SlotEnum.MELEE_WEAPON.toString())) {
            Weapon weapon = Weapon.builder()
                    .baseModificator(AttribEnum.STRENGTH)
                    .weaponMastery(itemDTO.getWeaponMastery())
                    .isFinesse(itemDTO.getIsFinesse())
                    .isUniversal(itemDTO.getIsUniversal())
                    .numberOfDice(itemDTO.getNumberOfDice())
                    .qualityOfDice(itemDTO.getQualityOfDice())
                    .build();
            item.setWeapon(weapon);
        } else if (itemDTO.getType().equals(SlotEnum.RANGED_WEAPON.toString())) {
            Weapon weapon = Weapon.builder()
                    .baseModificator(AttribEnum.DEXTERITY)
                    .weaponMastery(itemDTO.getWeaponMastery())
                    .isFinesse(itemDTO.getIsFinesse())
                    .isUniversal(itemDTO.getIsUniversal())
                    .numberOfDice(itemDTO.getNumberOfDice())
                    .qualityOfDice(itemDTO.getQualityOfDice())
                    .build();
            item.setWeapon(weapon);
        }
        return item;
    }

    public static ItemDTO fromItem(Item item, Boolean isEquipped) {
        ItemDTO itemDTO = ItemDTO.builder()
                .id(item.getId())
                .type(item.getSlot().getName())
                .name(item.getName())
                .description(item.getDescription())
                .weight(item.getWeight())
                .isEquipped(isEquipped)
                .build();
        if (item.getArmor() != null) {
            Armor armor = item.getArmor();
            itemDTO.setAc(armor.getAc());
            itemDTO.setArmorType(armor.getArmorType());
            itemDTO.setWeaponMastery(MasteryEnum.getMasteryByName(armor.getArmorType().getName()));
            itemDTO.setStealthDisadvantage(armor.getStealthDisadvantage());
        }
        if (item.getWeapon() != null) {
            Weapon weapon = item.getWeapon();
            itemDTO.setWeaponMastery(weapon.getWeaponMastery());
            itemDTO.setNumberOfDice(weapon.getNumberOfDice());
            itemDTO.setQualityOfDice(weapon.getQualityOfDice());
            itemDTO.setBaseModificator(weapon.getBaseModificator());
            itemDTO.setIsFinesse(weapon.getIsFinesse());
            itemDTO.setIsUniversal(weapon.getIsUniversal());
        }
        return itemDTO;
    }
}
