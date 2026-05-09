package com.chirayu.petmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class Base {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
