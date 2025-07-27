package ru.maleth.mythra.model.items;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.ItemTypeEnum;
import ru.maleth.mythra.enums.SlotEnum;
import ru.maleth.mythra.model.Character;

@Entity
@Table(name = "items")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long weight;
    private SlotEnum slot;
    private ItemTypeEnum type;
    private Integer numberOfUses;
    private Integer enchantment;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

}
