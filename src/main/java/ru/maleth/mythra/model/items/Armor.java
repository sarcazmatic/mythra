package ru.maleth.mythra.model.items;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "armors")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Armor extends Item {

    private Integer armor;

}
