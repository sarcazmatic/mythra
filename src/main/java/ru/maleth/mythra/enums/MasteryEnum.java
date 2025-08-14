package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum MasteryEnum {
    LIGHT_ARMOR("Легкие доспехи"),
    MEDIUM_ARMOR("Средние доспехи"),
    HEAVY_ARMOR("Тяжелые доспехи"),
    SHIELD("Щиты"),

    //SIMPLE_MELEE("Простое рукопашное оружие"),
    CLUB("Дубинка"),
    DAGGER("Кинжал"),
    GREATCLUB("Тяжёлая дубина"),
    HANDAXE("Топор"),
    JAVELIN("Метательное копьё"),
    LIGHT_HAMMER("Лёгкий молот"),
    MACE("Булавa"),
    QUARTERSTAFF("Посох"),
    SICKLE("Серп"),
    //MARTIAL_MELEE("Воинское рукопашное оружие"),
    BATTLEAXE("Боевой топор"),
    FLAIL("Цеп"),
    GREATAXE("Грандиозный топор"),
    GREATSWORD("Двуручный меч"),
    HALBERD("Алебарда"),
    LANCE("Копьё (лансерская пика)"),
    LONGSWORD("Длинный меч"),
    MAUL("Кувалда"),
    MORNINGSTAR("Утренняя звезда"),
    PIKE("Пика"),
    RAPIER("Рапира"),
    SCIMITAR("Скимитар"),
    SHORTSWORD("Короткий меч"),
    TRIDENT("Трезубец"),
    WAR_PICK("Боевая кирка"),
    WARHAMMER("Боевой молот"),
    WHIP("Кнут"),
    //SIMPLE_RANGED("Простое дальнобойное оружие"),
    LIGHT_CROSSBOW("Лёгкий арбалет"),
    DART("Дротик"),
    SLING("Праща"),
    //MATIAL_RANGED("Воинское дальнобойное оружие");
    HAND_CROSSBOW("Ручной арбалет"),
    HEAVY_CROSSBOW("Тяжёлый арбалет"),
    LONGBOW("Длинный лук"),
    NET("Сеть");

    private final String name;

    MasteryEnum(String name) {this.name = name;}

    public static MasteryEnum getMasteryByName(String name) {
        for (MasteryEnum m : MasteryEnum.values()) {
            if (m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }
}
