package com.chirayu.flowgrid.service;

import com.chirayu.flowgrid.dto.PetDTO;

/**
 * @author chirayu
 * @created 2026-05-07
 */


public interface PetService {

    Integer savePet(PetDTO petDTO);
    PetDTO findById(Integer petId);
    void updatePetDetails(Integer petId, String petName);
    void deletePet(Integer petId);
}
