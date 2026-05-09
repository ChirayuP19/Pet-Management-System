package com.chirayu.petmanagement.dto;


import com.chirayu.petmanagement.enums.PetType;
import lombok.Getter;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-03
 */
@Getter
@ToString
public class PetGenderStatisticDTO {
    long total;
    private Map<PetType, Long> type = new EnumMap<>(PetType.class);

    public void incrementTotal(long count) {
        this.total += count;
    }

    public void mergeOrCreateType(PetType type, long count) {
        this.type.merge(type, count, Long::sum);
    }
}
