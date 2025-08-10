package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;
import ru.maleth.mythra.enums.ArmorTypeEnum;
import ru.maleth.mythra.enums.SlotEnum;

@Builder
@Data
public class ItemDTO {

    private SlotEnum type;
    private String name;
    private ArmorTypeEnum armorType;
    private String description;
    private float weight;

}
