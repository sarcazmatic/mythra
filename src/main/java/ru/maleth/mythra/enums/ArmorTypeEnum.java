package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum ArmorTypeEnum {
    HEAVY("Тяжелый"),
    MEDIUM("Средний"),
    LIGHT("Легкий");

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
