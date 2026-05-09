package com.chirayu.petmanagement.entity;


import com.chirayu.petmanagement.enums.Gender;
import com.chirayu.petmanagement.enums.PetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pet_table")
public abstract class Pet extends Base {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column(name = "type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private PetType type;
    @Column(name = "breed", nullable = false)
    private String breed;

}