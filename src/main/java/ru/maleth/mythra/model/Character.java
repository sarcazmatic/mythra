package ru.maleth.mythra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "characters")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String charName;
    @ManyToOne
    @JoinColumn (name = "race_id")
    private Race charRace;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int experience;
    @Column(name = "armor_class")
    private int armorClass;
    private int initiative;
    @OneToOne
    @JoinColumn(name = "main_class_id")
    private CharClass mainClass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "characters_proficiencies",
            joinColumns = {@JoinColumn(name = "fk_character")},
            inverseJoinColumns = {@JoinColumn(name = "fk_proficiency")})
    private Set<Proficiency> proficiencies;

    @Column(name = "max_hp")
    private int maxHP;
    @Column(name = "current_hp")
    private int currentHP;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;
    @Column(name = "is_level_up_ready")
    private Boolean isLevelUpReady;
    @Column(name = "is_feat_or_stats_ready")
    private Boolean isFeatOrStatsReady;
}
