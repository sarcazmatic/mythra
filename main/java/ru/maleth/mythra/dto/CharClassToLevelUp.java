package ru.maleth.mythra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharClassToLevelUp {

    private String charClassToLevelUp;
    private String charName;

}
