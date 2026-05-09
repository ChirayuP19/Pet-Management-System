package com.chirayu.flowgrid.service;

import com.chirayu.flowgrid.dto.OwnerDTO;
import com.chirayu.flowgrid.exception.OwnerNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author chirayu
 * @created 2026-05-01
 */

public interface OwnerService {
    OwnerDTO getOwnerById(Long ownerId);

    Long saveOwner(OwnerDTO ownerDTO);

    void updatePetDetails(Long ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(Long ownerId) throws OwnerNotFoundException;

    Page<OwnerDTO> findAllOwners(Pageable pageable);
}
