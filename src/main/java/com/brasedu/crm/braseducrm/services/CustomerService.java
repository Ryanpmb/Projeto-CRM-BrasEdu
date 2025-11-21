package com.brasedu.crm.braseducrm.services;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.CreateCustomerDto;
import com.brasedu.crm.braseducrm.entities.CustomerEntity;
import com.brasedu.crm.braseducrm.entities.UserEntity;
import com.brasedu.crm.braseducrm.enums.OportunityStatus;
import com.brasedu.crm.braseducrm.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;

    public CustomerEntity store(CreateCustomerDto newCustumer) 
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(newCustumer.name);
        userEntity.setEmail(newCustumer.email);
        userEntity.setPhone(newCustumer.phone);
        userEntity.setBirthDate(newCustumer.birthDate);
        UserEntity storedUser = userService.store(userEntity);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(storedUser.getId());
        customerEntity.setLeadStatus(OportunityStatus.NEW);
        customerEntity.setOrigin(newCustumer.origin);
    }
}
