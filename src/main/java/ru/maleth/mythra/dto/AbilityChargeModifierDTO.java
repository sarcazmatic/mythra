package ru.maleth.mythra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AbilityChargeModifierDTO {

    private Long charId;
    private String abilName;
    private Integer modifier;
    private Boolean isFromClass;

}
