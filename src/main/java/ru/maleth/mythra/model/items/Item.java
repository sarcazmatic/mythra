package ru.maleth.mythra.model.items;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Long id;
    private String name;
    private String description;
    @Builder.Default
    private Double weight = 0.00;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SlotEnum slot = SlotEnum.MISC;

}
