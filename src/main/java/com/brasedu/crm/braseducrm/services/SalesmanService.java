package com.brasedu.crm.braseducrm.services;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.CreateSalesmanDto;
import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.entities.UserEntity;
import com.brasedu.crm.braseducrm.repositories.SalesmanRepository;
import com.brasedu.crm.braseducrm.security.SecurityConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesmanService {
    private final SalesmanRepository salesmanRepository;
    private final UserService userService;
    private final SecurityConfig securityConfig;

    public SalesmanEntity store(CreateSalesmanDto newSalesman) 
    {
        UserEntity userEntity = this.parseSalesmanDtoToUserEntity(newSalesman);
        UserEntity storedUser = userService.store(userEntity);

        if(storedUser == null) {
            throw new RuntimeException("Failed to store user");
        }  

        SalesmanEntity salesmanEntity = new SalesmanEntity();
        salesmanEntity.setId(storedUser.getId());
        String passwordBcripted = securityConfig.passwordEncoder().encode(newSalesman.password);
        salesmanEntity.setPassword(passwordBcripted);
        salesmanEntity.setDepartament(newSalesman.departament);
        return salesmanRepository.save(salesmanEntity);
    }

    public SalesmanEntity findById(String id) {
        return salesmanRepository.findById(id).orElse(null);
    }

    public SalesmanEntity update(SalesmanEntity newSalesmanInformations, String id)
    {
        SalesmanEntity olderSalesman = this.findById(id);

        if(olderSalesman == null) {
            throw new RuntimeException("Customer not found");
        }

        olderSalesman.setDepartament(newSalesmanInformations.getDepartament());
        return salesmanRepository.save(olderSalesman);
    }

    public Iterable<SalesmanEntity> findAll()
    {
        return salesmanRepository.findAll();
    }

    public void deleteById(String id)
    {
        salesmanRepository.deleteById(id);
    }


    private UserEntity parseSalesmanDtoToUserEntity(CreateSalesmanDto salesmanDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(salesmanDto.name);
        userEntity.setEmail(salesmanDto.email);
        userEntity.setPhone(salesmanDto.phone);
        userEntity.setBirthDate(salesmanDto.birthDate);
        return userEntity;
    }

}
