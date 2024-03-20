package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewCharacterDto {

    private String charName;
    private String charClass;
    private String charRace;
    private String charSubrace;

}
