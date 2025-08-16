package ru.maleth.mythra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.maleth.mythra.enums.ArmorTypeEnum;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.MasteryEnum;
import ru.maleth.mythra.enums.SlotEnum;

@Builder
@Data
public class ItemDTO {

    private Long id;
    @NotNull
    private String type;
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Double weight;
    private Boolean isEquipped;

    private ArmorTypeEnum armorType;
    private Integer ac;
    private Boolean stealthDisadvantage;

    private MasteryEnum weaponMastery;
    private Integer numberOfDice;
    private Integer qualityOfDice;
    private AttribEnum baseModificator;
    private Boolean isFinesse;
    private Boolean isUniversal;
    private Boolean isMasterful;

}
