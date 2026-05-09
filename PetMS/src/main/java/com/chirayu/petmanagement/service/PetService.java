package com.chirayu.petmanagement.service;


import com.chirayu.petmanagement.dto.PetDTO;
import com.chirayu.petmanagement.dto.PetStatisticDTO;

public interface PetService {
    PetStatisticDTO getStatistics();
    Integer savePet(PetDTO petDTO);
    PetDTO findPetById(Integer petId);
    void updatePetDetails(Integer petId, String petName);
    void deletePet(Integer petId);
}
