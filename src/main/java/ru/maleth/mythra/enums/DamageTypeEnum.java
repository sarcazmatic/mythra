package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum DamageTypeEnum {

    BLUDGEONING("Дробящий"),
    PIERCING("Колющий"),
    SLASHING("Рубящий"),
    FIRE("Огонь"),
    COLD("Холод"),
    LIGHTNING("Электричество"),
    ACID("Кислота"),
    POISON("Яд"),
    FORCE("Силовое поле"),
    SOUND("Гром"),
    NECROTIC("Некротический"),
    RADIANT("Излучение"),
    PSYCHIC("Психический");

    private final String name;

    DamageTypeEnum(String name) {this.name = name;}

}
