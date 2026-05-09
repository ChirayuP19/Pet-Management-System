package com.chirayu.petmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@Getter
@Setter
@ToString
public class PetStatisticDTO {
    private long total;
    private Map<String, PetCategoryStatisticsDTO> category = new HashMap<>();

    public void incrementTotal(long count) {
        this.total += count;
    }

    public PetCategoryStatisticsDTO getOrCreateCategory(String category) {
        return this.category.computeIfAbsent(category, n -> new PetCategoryStatisticsDTO());
    }
}
