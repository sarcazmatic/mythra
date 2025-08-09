package ru.maleth.mythra.model.items;

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
import ru.maleth.mythra.model.characters.Character;

@Entity
@Table(name = "characters_items")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "number_of_uses")
    @Builder.Default
    private Integer numberOfUses = 0;
}
