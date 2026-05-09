package com.chirayu.petmanagement.controller;


import com.chirayu.petmanagement.dto.PetDTO;
import com.chirayu.petmanagement.dto.PetStatisticDTO;
import com.chirayu.petmanagement.dto.UpdatePetDTO;
import com.chirayu.petmanagement.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author chirayu
 * @created 2026-05-03
 */
@RestController
@RequestMapping("/api/v1/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private final PetService petService;

    @GetMapping("/stats")
    public ResponseEntity<PetStatisticDTO> getStatistics() {
        log.info("Getting pet statistics");
        PetStatisticDTO statistics = petService.getStatistics();
        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }

    @PostMapping
    public ResponseEntity<Integer> savePet(@RequestBody PetDTO petId) {
        log.info("Saving pet with id: {}", petId);
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.savePet(petId));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetDTO> findPetById(@PathVariable Integer petId) {
        log.info("Finding pet with id: {}", petId);
        return ResponseEntity.status(HttpStatus.OK).body(petService.findPetById(petId));
    }

    @PatchMapping("/{petId}")
    public ResponseEntity<Void> updatePetDetails(@PathVariable Integer petId, @RequestBody UpdatePetDTO updatePetDTO) {
        log.info("Updating pet details for pet with id: {}", petId);
        petService.updatePetDetails(petId, updatePetDTO.name());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Integer petId) {
        log.info("Deleting pet with id: {}", petId);
        petService.deletePet(petId);
        return ResponseEntity.noContent().build();
    }
}
