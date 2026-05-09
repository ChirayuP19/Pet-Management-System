package com.chirayu.petmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "wild_pet_table")
public class WildPet extends Pet{
    @Column(name = "place_of_birth", nullable = false)
    private String birthPlace;
}
