package ru.maleth.mythra.model.characters;

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
import ru.maleth.mythra.enums.CustomEditEnum;

@Table(name = "custom_edits")
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CharCustomEdits {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @ManyToOne
        @JoinColumn(name = "character_id")
        private Character character;
        @Enumerated(EnumType.STRING)
        @Column(name = "custom_edit")
        private CustomEditEnum customEdits;
        @Column(name = "modificator")
        private Integer modificator;
}
