package com.brasedu.crm.braseducrm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.entities.InterationEntity;
import com.brasedu.crm.braseducrm.repositories.InterationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterationService {
        private final InterationRepository interationRepository;

    public InterationEntity include(InterationEntity interation) {
        return interationRepository.save(interation);
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

    public List<InterationEntity> listAll() {
        return interationRepository.findAll();
    }

    public void delete(Integer id) {
        interationRepository.deleteById(id);
    }
}
