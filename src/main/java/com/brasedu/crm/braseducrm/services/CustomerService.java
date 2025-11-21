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
        UserEntity userEntity = this.parseCustumerDtoToUserEntity(newCustumer);
        UserEntity storedUser = userService.store(userEntity);

        if(storedUser == null) {
            throw new RuntimeException("Failed to store user");
        }  

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(storedUser.getId());
        customerEntity.setLeadStatus(OportunityStatus.NEW);
        customerEntity.setOrigin(newCustumer.origin);
        return customerRepository.save(customerEntity);
    }

    public CustomerEntity findById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerEntity update(CustomerEntity newCustomerInformations, String id)
    {
        CustomerEntity olderCustomer = this.findById(id);

        if(olderCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        olderCustomer.setLeadStatus(newCustomerInformations.getLeadStatus());
        olderCustomer.setOrigin(newCustomerInformations.getOrigin());
        return customerRepository.save(olderCustomer);
    }

    public Iterable<CustomerEntity> findAll()
    {
        return customerRepository.findAll();
    }

    public void deleteById(String id)
    {
        customerRepository.deleteById(id);
    }


    private UserEntity parseCustumerDtoToUserEntity(CreateCustomerDto customerDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(customerDto.name);
        userEntity.setEmail(customerDto.email);
        userEntity.setPhone(customerDto.phone);
        userEntity.setBirthDate(customerDto.birthDate);
        return userEntity;
    }
}
