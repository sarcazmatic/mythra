package ru.maleth.mythra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;

@Entity
@Table(name = "abilities")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "ability_source")
    private AbilityEnum abilitySource;
    @Column(name = "is_active")
    private boolean isActive;
    @Enumerated(EnumType.STRING)
    private ActionCostEnum cost;
    @Column(name = "requires_rest")
    private boolean requiresRest;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_rest")
    private RestEnum typeOfRest;
    @Column(name = "level_available")
    private Integer levelAvailable;
    @ManyToOne
    @JoinColumn(name = "class_source_id")
    private CharClass charClass;
    @ManyToOne
    @JoinColumn(name = "race_source_id")
    private Race race;
}
