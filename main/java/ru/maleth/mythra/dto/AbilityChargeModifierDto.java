package ru.maleth.mythra.dto;

import lombok.*;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AbilityChargeModifierDto {

    private Long charId;
    private String abilName;
    private Integer modifier;
    private Boolean isFromClass;

}
