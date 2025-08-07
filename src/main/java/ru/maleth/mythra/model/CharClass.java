package ru.maleth.mythra.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.maleth.mythra.enums.AttribEnum;

@Table(name = "classes")
@Entity
@Data
@RequiredArgsConstructor
public class CharClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "saving_throw_one")
    private AttribEnum savingThrowOne;
    @Enumerated(EnumType.STRING)
    @Column(name = "saving_throw_two")
    private AttribEnum savingThrowTwo;

}
