package ru.maleth.mythra.model.items;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "weapons")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Weapon {

    @Id
    private Long id;
    @Column(name = "number_of_dice")
    private Integer numberOfDice;
    @Column(name = "quality_of_dice")
    private Integer qualityOfDice;
    @Column(name = "base_modificator")
    private Integer baseModificator; //ловкость или сила
    @Column(name = "is_universal")
    private Boolean isUniversal;
    @Column(name = "is_finesse")
    private Boolean isFinesse;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
