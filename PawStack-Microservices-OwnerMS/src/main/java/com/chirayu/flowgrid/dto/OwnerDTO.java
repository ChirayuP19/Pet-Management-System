package com.chirayu.flowgrid.dto;

import com.chirayu.flowgrid.enums.Gender;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
public class OwnerDTO {
    @EqualsAndHashCode.Include
    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String city;
    private String state;
    @EqualsAndHashCode.Include
    private String mobileNumber;
    @EqualsAndHashCode.Include
    private String emailId;
    private PetDTO petDTO;
}
