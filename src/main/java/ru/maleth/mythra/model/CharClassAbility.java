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
import lombok.NoArgsConstructor;

@Entity
@Table (name = "character_classes_abilities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharClassAbility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "char_class_id")
    private CharClass charClass;
    @ManyToOne
    @JoinColumn(name = "ability_id")
    private Ability ability;
    @Column(name = "number_of_uses")
    private Integer numberOfUses;
    @Column(name = "max_number_of_uses")
    private Integer maxNumberOfUses;
}
