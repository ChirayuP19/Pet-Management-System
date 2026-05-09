package com.chirayu.flowgrid.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@Getter
@Setter
@ToString(callSuper = true)
public class DomesticPetDTO extends PetDTO {

    private LocalDate birthDate;
}
