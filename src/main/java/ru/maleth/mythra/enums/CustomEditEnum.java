package ru.maleth.mythra.enums;

import lombok.Getter;

@Getter
public enum CustomEditEnum {

    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    CONSTITUTION("Телосложение"),
    INTELLIGENCE("Интеллект"),
    WISDOM("Мудрость"),
    CHARISMA("Харизма"),
    ACROBATICS("Акробатика"),
    ATHLETICS("Атлетика"),
    ARCANA("Аркана"),
    DECEPTION("Обман"),
    HISTORY("История"),
    INITIATIVE("Инициатива"),
    INSIGHT("Проницательность"),
    INTIMIDATION("Запугивание"),
    INVESTIGATION("Расследование"),
    MEDICINE("Медицина"),
    NATURE("Природа"),
    PERCEPTION("Восприятие"),
    PERFORMANCE("Выступление"),
    PERSUASION("Убеждение"),
    RELIGION("Религия"),
    SLEIGHT_OF_HAND("Ловкость рук"),
    STEALTH("Скрытность"),
    SURVIVAL("Выживание"),
    ANIMAL_HANDLING("Обращение с животными");

    private final String name;

    CustomEditEnum(String name) {
        this.name = name;
    }

    public static CustomEditEnum getCustomEditByName(String name) {
        for (CustomEditEnum cee : CustomEditEnum.values()) {
            if (cee.getName().equals(name)) {
                return cee;
            }
        }
        return null;
    }
}
