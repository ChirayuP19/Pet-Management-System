package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.PetDTO;
import com.chirayu.flowgrid.dto.UpdatePetDTO;
import com.chirayu.flowgrid.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * @author chirayu
 * @created 2026-05-07
 */
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final RestClient restClient;
    @Value("${pet.service.base.url}")
    private String petServiceUrl;

    @Override
    public Integer savePet(PetDTO petDTO) {
        ResponseEntity<Integer> response = restClient.post()
                .uri(petServiceUrl)
                .body(petDTO)
                .retrieve()
                .toEntity(Integer.class);
        return response.getBody();
    }

    @Override
    public PetDTO findById(Integer petId) {
        ResponseEntity<PetDTO> response = restClient.get()
                .uri(petServiceUrl + "/{petId}", petId)
                .retrieve()
                .toEntity(PetDTO.class);
        return response.getBody();
    }

    @Override
    public void updatePetDetails(Integer petId, String petName) {
        UpdatePetDTO updatePetDTO = new UpdatePetDTO(petName);
        restClient.patch()
                .uri(petServiceUrl + "/{petId}", petId, petName)
                .body(updatePetDTO)
                .retrieve()
                .toEntity(String.class);
    }

    @Override
    public void deletePet(Integer petId) {
        restClient.delete()
                .uri(petServiceUrl + "/{petId}", petId)
                .retrieve()
                .toBodilessEntity();
    }
}
