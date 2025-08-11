package ru.maleth.mythra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ru.maleth.mythra.enums.ArmorTypeEnum;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.SlotEnum;

@Builder
@Data
public class ItemDTO {

    @NotNull
    private SlotEnum type;
    @NotBlank
    private String name;
    @NotNull
    private String description;
    @NotNull
    private Double weight;

    private ArmorTypeEnum armorType;
    private Integer ac;
    private Boolean stealthDisadvantage;

    private Integer numberOfDice;
    private Integer qualityOfDice;
    private AttribEnum baseModificator;
    private Boolean isFinesse;
    private Boolean isUniversal;

}
