package com.chirayu.petmanagement.dto;

import com.chirayu.petmanagement.enums.Gender;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@ToString
@Getter
public class PetCategoryStatisticsDTO {
    private long total;
    private Map<Gender, PetGenderStatisticDTO> gender = new EnumMap<>(Gender.class);

    public void incrementTotal(long count) {
        this.total += count;
    }

    public PetGenderStatisticDTO getOrCreateGender(Gender gender) {
        return this.gender.computeIfAbsent(gender, n -> new PetGenderStatisticDTO());
    }
}
