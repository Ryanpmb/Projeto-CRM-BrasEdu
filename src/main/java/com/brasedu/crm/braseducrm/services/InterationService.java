package com.brasedu.crm.braseducrm.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.request.CreateInterationDTO;
import com.brasedu.crm.braseducrm.dto.response.ResponseInterationDTO;
import com.brasedu.crm.braseducrm.entities.InterationEntity;
import com.brasedu.crm.braseducrm.entities.OportunityEntity;
import com.brasedu.crm.braseducrm.repositories.InterationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterationService {
    private final InterationRepository interationRepository;
    private final OportunityService oportunityService;

    public InterationEntity include(CreateInterationDTO interation) {
        InterationEntity newInteration = new InterationEntity();
        OportunityEntity oportunityEntity = oportunityService
                .findById(interation.getOpportunityId());
                
        if (oportunityEntity != null) {
            newInteration.setOportunity(oportunityEntity);
            newInteration.setInterationDate(LocalDate.now());
            newInteration.setType(interation.getType());
            newInteration.setDescription(interation.getDescription());
        }

        return interationRepository.save(newInteration);
    }

    public InterationEntity edit(int id, InterationEntity interation) {
        Optional<InterationEntity> existingInteration = interationRepository.findById(id);

        if (existingInteration.isPresent()) {
            InterationEntity updatedInteration = existingInteration.get();

            updatedInteration.setOportunity(interation.getOportunity());
            updatedInteration.setInterationDate(interation.getInterationDate());
            updatedInteration.setType(interation.getType());
            updatedInteration.setDescription(interation.getDescription());

            return interationRepository.save(updatedInteration);
        } else {
            return null;
        }
    }

    public List<ResponseInterationDTO> listAll() {
        List<InterationEntity> interations = interationRepository.findAll();
        return interations.stream().map(ResponseInterationDTO::new).toList();
    }

    public void delete(Integer id) {
        interationRepository.deleteById(id);
    }
}
