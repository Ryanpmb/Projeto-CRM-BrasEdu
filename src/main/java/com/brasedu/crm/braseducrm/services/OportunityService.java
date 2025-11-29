package com.brasedu.crm.braseducrm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.CreateOpportunityDto;
import com.brasedu.crm.braseducrm.entities.CourseEntity;
import com.brasedu.crm.braseducrm.entities.CustomerEntity;
import com.brasedu.crm.braseducrm.entities.OportunityEntity;
import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.repositories.OportunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OportunityService {
    private final OportunityRepository oportunityRepository;
    private final CourseService courseService;
    private final CustomerService customerService;
    private final SalesmanService salesmanService;

    public OportunityEntity include(CreateOpportunityDto oportunity) {
        CourseEntity course = this.courseService.findById(oportunity.getCourseId());
        CustomerEntity customer = this.customerService.findById(oportunity.getCustomerId());
        SalesmanEntity salesman = this.salesmanService.findById(oportunity.getSalesmanId());

        OportunityEntity oportunityEntity = new OportunityEntity();
        oportunityEntity.setCourse(course);
        oportunityEntity.setCustomer(customer);
        oportunityEntity.setSalesman(salesman);
        oportunityEntity.setSalesStatus(oportunity.getStatus());

        return oportunityRepository.save(oportunityEntity);
    }

    public OportunityEntity edit(int id, OportunityEntity oportunity) {
        Optional<OportunityEntity> existingOportunity = oportunityRepository.findById(id);

        if (existingOportunity.isPresent()) {
            OportunityEntity updatedOportunity = existingOportunity.get();

            updatedOportunity.setCustomer(oportunity.getCustomer());
            updatedOportunity.setSalesman(oportunity.getSalesman());
            updatedOportunity.setCourse(oportunity.getCourse());
            updatedOportunity.setInterations(oportunity.getInterations());
            updatedOportunity.setSale(oportunity.getSale());
            updatedOportunity.setSalesStatus(oportunity.getSalesStatus());
            updatedOportunity.setInitiatedAt(oportunity.getInitiatedAt());
            updatedOportunity.setFinishedAt(oportunity.getFinishedAt());

            return oportunityRepository.save(updatedOportunity);
        } else {
            return null;
        }
    }

    public List<OportunityEntity> listAll() {
        return oportunityRepository.findAll();
    }

    public void delete(Integer id) {
        oportunityRepository.deleteById(id);
    }
}
