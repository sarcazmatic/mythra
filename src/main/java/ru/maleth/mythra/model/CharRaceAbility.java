package ru.maleth.mythra.model;

import jakarta.persistence.Column;
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

@Entity
@Table (name = "character_race_abilities")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CharRaceAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "char_race_id")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Ability ability;
    @Column(name = "number_of_uses")
    private Integer numberOfUses;
    @Column(name = "max_number_of_uses")
    private Integer maxNumberOfUses;
}
