package ru.maleth.mythra.model.items;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "weapons")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Weapon extends Item {

    private Integer numberOfDice;
    private Integer qualityOfDice;
    private Integer baseModificator;

}
