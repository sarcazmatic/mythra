package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ArmorTypeEnum {
    HEAVY_ARMOR("Тяжелый"),
    MEDIUM_ARMOR("Средний"),
    LIGHT_ARMOR("Легкий");

    private final String name;

    ArmorTypeEnum (String name) {this.name = name;}

    public static ArmorTypeEnum getArmorTypeByName(String name) {
        for (ArmorTypeEnum a : ArmorTypeEnum.values()) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }
}
