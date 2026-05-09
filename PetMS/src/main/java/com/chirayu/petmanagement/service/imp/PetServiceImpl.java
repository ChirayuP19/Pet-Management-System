package com.chirayu.petmanagement.service.imp;


import com.chirayu.petmanagement.dto.PetCategoryStatisticsDTO;
import com.chirayu.petmanagement.dto.PetDTO;
import com.chirayu.petmanagement.dto.PetGenderStatisticDTO;
import com.chirayu.petmanagement.dto.PetStatisticDTO;
import com.chirayu.petmanagement.entity.Pet;
import com.chirayu.petmanagement.enums.Gender;
import com.chirayu.petmanagement.enums.PetType;
import com.chirayu.petmanagement.exception.PetNotFoundException;
import com.chirayu.petmanagement.mapper.PetMapper;
import com.chirayu.petmanagement.repository.PetRepository;
import com.chirayu.petmanagement.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chirayu
 * @created 2026-05-03
 */

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    @Override
    public Integer savePet(PetDTO petDTO) {
        return petRepository.save(petMapper.petDTOToPet(petDTO)).getId();
    }


    @Override
    public PetDTO findPetById(Integer petId) {
        return petRepository.findById(petId)
                .map(petMapper::petToPetDTO)
                .orElseThrow(() ->
                        new PetNotFoundException("Pet with id " + petId + " not found."));
    }

    @Override
    public void updatePetDetails(Integer petId, String petName) {
        Pet pet = petRepository.findById(petId).orElseThrow(() ->
                new PetNotFoundException("Pet with id " + petId + " not found."));
        if (petName != null && !petName.isBlank()) {
            pet.setName(petName);
            petRepository.save(pet);
        }
    }

    @Override
    public void deletePet(Integer petId) {
        if (!petRepository.existsById(petId)) {
            throw new PetNotFoundException("Pet with id " + petId + " not found.");
        }
        petRepository.deleteById(petId);
    }

    @Override
    public PetStatisticDTO getStatistics() {
        List<Object[]> rows = petRepository.getStatistics();
        PetStatisticDTO petStatisticDTO = new PetStatisticDTO();
        for (Object[] row : rows) {
            String category = (String) row[0];
            Gender gender = (Gender) row[1];
            PetType type = (PetType) row[2];
            Long count = (Long) row[3];
            petStatisticDTO.incrementTotal(count);
            PetCategoryStatisticsDTO petCategoryStatisticDTO = petStatisticDTO.getOrCreateCategory(category);
            petCategoryStatisticDTO.incrementTotal(count);
            PetGenderStatisticDTO petGenderStatistics = petCategoryStatisticDTO.getOrCreateGender(gender);
            petGenderStatistics.incrementTotal(count);
            petGenderStatistics.mergeOrCreateType(type, count);
        }
        return petStatisticDTO;
    }

}
