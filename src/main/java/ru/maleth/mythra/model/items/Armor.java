package ru.maleth.mythra.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.ArmorTypeEnum;

@Entity
@Table(name = "armors")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Armor {

    @Id
    private Long id;
    private Integer armor;
    @Column(name = "stealth_disadvantage")
    private Boolean stealthDisadvantage;
    @Enumerated(EnumType.STRING)
    @Column(name = "armor_type")
    private ArmorTypeEnum armorTypeEnum;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
