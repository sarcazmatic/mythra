package ru.maleth.mythra.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.MasteryEnum;

@Embeddable
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Getter
@Setter
public class Weapon {

    @Column(name = "number_of_dice")
    private Integer numberOfDice;
    @Column(name = "quality_of_dice")
    private Integer qualityOfDice;
    @Enumerated(EnumType.STRING)
    @Column(name = "base_modificator")
    private AttribEnum baseModificator; //ловкость или сила
    @Column(name = "is_universal")
    private Boolean isUniversal;
    @Column(name = "is_finesse")
    private Boolean isFinesse;
    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_mastery")
    private MasteryEnum weaponMastery;

}
