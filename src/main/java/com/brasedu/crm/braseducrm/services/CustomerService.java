package com.brasedu.crm.braseducrm.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.request.CreateCustomerDto;
import com.brasedu.crm.braseducrm.dto.response.ResponseCustomerDTO;
import com.brasedu.crm.braseducrm.entities.CustomerEntity;
import com.brasedu.crm.braseducrm.enums.OportunityStatus;
import com.brasedu.crm.braseducrm.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerEntity store(CreateCustomerDto newCustumer) throws Exception {
        try {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setName(newCustumer.name);
            customerEntity.setEmail(newCustumer.email);
            customerEntity.setPhone(newCustumer.phone);
            customerEntity.setBirthDate(newCustumer.birthDate);
            customerEntity.setLeadStatus(OportunityStatus.NEW);
            customerEntity.setOrigin(newCustumer.origin);
            customerEntity.setRegisterDate(LocalDate.now());
            return customerRepository.save(customerEntity);
        } catch (Exception e) {
            throw new Exception("Stored user fail");
        }
    }

    public CustomerEntity findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerEntity update(CustomerEntity newCustomerInformations, String id) {
        CustomerEntity olderCustomer = this.findById(id);

        if (olderCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        olderCustomer.setLeadStatus(newCustomerInformations.getLeadStatus());
        olderCustomer.setOrigin(newCustomerInformations.getOrigin());
        return customerRepository.save(olderCustomer);
    }

    public List<ResponseCustomerDTO> findAll() {
        List<CustomerEntity> customers = customerRepository.findAllByInactiveFalse();

        return customers.stream().map(ResponseCustomerDTO::new).toList();
    }

    public void deleteById(String id) {
        customerRepository.deleteById(id);
    }

}
