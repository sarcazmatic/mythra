package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ArmorTypeEnum {
    CLOTH("Ткань"),
    HEAVY_ARMOR("Тяжелые доспехи"),
    MEDIUM_ARMOR("Средние доспехи"),
    LIGHT_ARMOR("Легкие доспехи");

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
