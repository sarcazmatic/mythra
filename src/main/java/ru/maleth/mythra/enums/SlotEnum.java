package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum SlotEnum {

    RING("Кольцо"),
    AMULET("Амулет"),
    RANGED_WEAPON("Стрелковое оружие"),
    MELEE_WEAPON("Рукопашное оружие"),
    AMMUNITION("Амуниция"),
    HELM("Доспех"),
    SHIELD("Щит"),
    CUIRASS("Кираса"),
    PAULDRONS("Наплечники"),
    GAUNTLETS("Перчатки"),
    LEGGINGS("Поножи"),
    CLOAK("Плащ"),
    MISC("Прочее"),
    CONSUMABLE("Полезное"),
    TEXTS("Тексты");


    private final String name;

    SlotEnum(String name) {
        this.name = name;
    }

}
