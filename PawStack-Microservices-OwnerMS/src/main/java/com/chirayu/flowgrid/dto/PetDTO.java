package com.chirayu.flowgrid.dto;

import com.chirayu.flowgrid.enums.Gender;
import com.chirayu.flowgrid.enums.PetType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({@Type(value = DomesticPetDTO.class, name = "Domestic"),
        @Type(value = WildPetDTO.class, name = "Wild")})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
public class PetDTO {
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private Gender gender;
    private PetType type;
    private String breed;
}
