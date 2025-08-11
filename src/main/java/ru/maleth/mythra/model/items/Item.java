package ru.maleth.mythra.model.items;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.SlotEnum;

@Entity
@Table(name = "items")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Builder.Default
    private Double weight = 0.00;
    @Enumerated(EnumType.STRING)
    private SlotEnum slot;
    @Embedded
    private Armor armor;
    @Embedded
    private Weapon weapon;

}
