package ru.maleth.mythra.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer numberOfUses;
}
