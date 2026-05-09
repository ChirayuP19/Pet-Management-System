package com.chirayu.flowgrid.service.imp;

import com.chirayu.flowgrid.dto.MailDTO;
import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.dto.PetDTO;
import com.chirayu.flowgrid.entity.Owner;
import com.chirayu.flowgrid.enums.MailType;
import com.chirayu.flowgrid.exception.OwnerNotFoundException;
import com.chirayu.flowgrid.repository.OwnerRepository;
import com.chirayu.flowgrid.service.MailService;
import com.chirayu.flowgrid.service.OwnerService;
import com.chirayu.flowgrid.service.PetService;
import com.chirayu.flowgrid.util.OwnerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author chirayu
 * @created 2026-05-01
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final PetService petService;
    @Value("${owner.not.found}")
    private String ownerNotFoundMessage;
    private final MailService mailService;

    @Override
    public OwnerDTO getOwnerById(Long ownerId) {
        log.info("Fetching owner with ID: {}", ownerId);
        return ownerRepository.findById(ownerId)
                .map(o -> {
                    PetDTO petDTO = petService.findById(o.getPetId());
                    log.info("Pet DTO: {}", petDTO);
                    OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(o);
                    ownerDTO.setPetDTO(petDTO);
                    return ownerDTO;
                })
                .orElseThrow(() -> {
                    log.warn("Owner not found with ID: {}", ownerId);
                    return new OwnerNotFoundException(String.format(ownerNotFoundMessage, ownerId));
                });
    }

    @Override
    public Long saveOwner(OwnerDTO ownerDTO) {
        Integer petId = petService.savePet(ownerDTO.getPetDTO());
        ownerDTO.getPetDTO().setId(petId);
        Owner owner = ownerMapper.ownerDTOToOwner(ownerDTO);
        log.info("Saving owner: {}", owner);
        ownerRepository.save(owner);
        MailDTO mailDTO = new MailDTO(ownerDTO.getEmailId(), ownerDTO.getFirstName(), ownerDTO.getLastName(), MailType.WELCOME);
        log.info("MailDTO: {}", mailDTO);
        mailService.sendMail(mailDTO);
        log.info("Owner saved with ID: {}", owner.getId());
        log.info("Save Mail sent to {}AT:: {}", ownerDTO.getEmailId(), LocalDateTime.now());
        return owner.getId();
    }


    @Override
    public void updatePetDetails(Long ownerId, String petName) throws OwnerNotFoundException {
        log.info("Updating pet details for owner ID: {}, new name: {}", ownerId, petName);
        Owner owner = ownerRepository.findById(ownerId)
                .map(o -> {
                    log.debug("Found owner: {}", o);
                    petService.updatePetDetails(o.getPetId(), petName);
                    return o;
                })
                .orElseThrow(() -> {
                    log.warn("Owner not found for update, ID: {}", ownerId);
                    return new OwnerNotFoundException(String.format(ownerNotFoundMessage, ownerId));
                });
        mailService.sendMail(new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), MailType.MODIFY));
        log.info("Pet details updated and modify mail sent for owner ID: {}", ownerId);
    }

    @Override
    public void deleteOwner(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .map(o -> {
                    petService.deletePet(o.getPetId());
                    return o;
                })
                .orElseThrow(() -> new OwnerNotFoundException
                        (String.format(ownerNotFoundMessage, ownerId)));
        ownerRepository.deleteById(ownerId);
        mailService.sendMail(new MailDTO(owner.getEmailId(), owner.getFirstName(), owner.getLastName(), MailType.EXIT));
        log.info("Owner deleted & Email send with ID: {}", ownerId);
        log.info("Delete Mail sent to {}AT:: {}", owner.getEmailId(), LocalDateTime.now());
    }

    @Override
    public Page<OwnerDTO> findAllOwners(Pageable pageable) {
        log.info("Fetching all owners with pageable: {}", pageable);
        return ownerRepository.findAll(pageable)
                .map(o -> {
                    log.debug("Processing owner ID: {}", o.getId());
                    PetDTO petDTO = petService.findById(o.getPetId());
                    OwnerDTO ownerDTO = ownerMapper.ownerToOwnerDTO(o);
                    ownerDTO.setPetDTO(petDTO);
                    return ownerDTO;
                });
    }


}
