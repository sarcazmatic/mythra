package ru.maleth.mythra.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.maleth.mythra.enums.ArmorTypeEnum;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Armor {

    @Column(name = "armor")
    private Integer ac;
    @Column(name = "stealth_disadvantage")
    private Boolean stealthDisadvantage;
    @Enumerated(EnumType.STRING)
    @Column(name = "armor_type")
    private ArmorTypeEnum armorType;

}
