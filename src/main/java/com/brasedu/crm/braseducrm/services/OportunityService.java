package com.brasedu.crm.braseducrm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.request.CreateOpportunityDto;
import com.brasedu.crm.braseducrm.dto.request.UpdateOpportunityDTO;
import com.brasedu.crm.braseducrm.dto.response.ResponseOportunityDTO;
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

    public ResponseOportunityDTO include(CreateOpportunityDto oportunity) {
        CourseEntity course = this.courseService.findById(oportunity.getCourseId());
        CustomerEntity customer = this.customerService.findById(oportunity.getCustomerId());
        SalesmanEntity salesman = this.salesmanService.findById(oportunity.getSalesmanId());

        OportunityEntity oportunityEntity = new OportunityEntity();
        oportunityEntity.setCourse(course);
        oportunityEntity.setCustomer(customer);
        oportunityEntity.setSalesman(salesman);
        oportunityEntity.setSalesStatus(oportunity.getSalesStatus());
        oportunityEntity.setInitiatedAt(LocalDate.now());
        oportunityRepository.save(oportunityEntity);

        return new ResponseOportunityDTO(oportunityEntity);
    }

    public ResponseOportunityDTO edit(int id, UpdateOpportunityDTO oportunity) {
        OportunityEntity existingOportunity = oportunityRepository.findById(id).orElse(null);

        if (existingOportunity != null) {
            CourseEntity course = this.courseService.findById(oportunity.getCourseId());
            SalesmanEntity salesman = this.salesmanService.findById(oportunity.getSalesmanId());

            existingOportunity.setSalesman(salesman);
            existingOportunity.setCourse(course);
            System.out.println(oportunity.getSalesStatus());
            existingOportunity.setSalesStatus(oportunity.getSalesStatus());
            existingOportunity.setFinishedAt(oportunity.getFinishedAt());
            oportunityRepository.save(existingOportunity);
            ResponseOportunityDTO response = new ResponseOportunityDTO(existingOportunity);

            return response;
        } else {
            return null;
        }
    }

    public List<ResponseOportunityDTO> listAll() {
        List<OportunityEntity> opportunities = oportunityRepository.findAll();

        return opportunities.stream().map(ResponseOportunityDTO::new).toList();
    }

    public void delete(Integer id) {
        oportunityRepository.deleteById(id);
    }
}
