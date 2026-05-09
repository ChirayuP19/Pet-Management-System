package com.chirayu.flowgrid.entity;

import com.chirayu.flowgrid.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "owner_table")
@Getter
@Setter
@Entity
public class Owner extends Base {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;
    @Column(name = "mobile_number", nullable = false, unique = true, length = 10)
    private String mobileNumber;
    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;
    @Column(name = "pet_id", nullable = false, unique = true)
    private Integer petId;
}

