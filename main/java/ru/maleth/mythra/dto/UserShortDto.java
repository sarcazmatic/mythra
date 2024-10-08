package ru.maleth.mythra.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserShortDto {

    @NotBlank
    private String name;

}
