package com.brasedu.crm.braseducrm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.dto.request.CreateSalesmanDto;
import com.brasedu.crm.braseducrm.dto.response.ResponseSalesmanDTO;
import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.repositories.SalesmanRepository;
import com.brasedu.crm.braseducrm.security.SecurityConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalesmanService {
    private final SalesmanRepository salesmanRepository;
    private final SecurityConfig securityConfig;

    public SalesmanEntity store(CreateSalesmanDto newSalesman) {
        SalesmanEntity salesmanEntity = new SalesmanEntity();
        salesmanEntity.setName(newSalesman.name);
        salesmanEntity.setEmail(newSalesman.email);
        salesmanEntity.setPhone(newSalesman.phone);
        salesmanEntity.setBirthDate(newSalesman.birthDate);
        String passwordBcripted = securityConfig.passwordEncoder().encode(newSalesman.password);
        salesmanEntity.setPassword(passwordBcripted);
        salesmanEntity.setDepartament(newSalesman.departament);
        return salesmanRepository.save(salesmanEntity);
    }

    public SalesmanEntity findById(String id) {
        return salesmanRepository.findById(id).orElse(null);
    }

    public SalesmanEntity update(SalesmanEntity newSalesmanInformations, String id) {
        SalesmanEntity olderSalesman = this.findById(id);

        if (olderSalesman == null) {
            throw new RuntimeException("Customer not found");
        }

        olderSalesman.setDepartament(newSalesmanInformations.getDepartament());
        return salesmanRepository.save(olderSalesman);
    }

    public Iterable<ResponseSalesmanDTO> findAll() {
        List<SalesmanEntity> salesmans = salesmanRepository.findAll();

        return salesmans.stream().map(ResponseSalesmanDTO::new).toList();
    }

    public void deleteById(String id) {
        salesmanRepository.deleteById(id);
    }

    public SalesmanEntity authenticate(String email, String rawPassword) {
        SalesmanEntity salesman = salesmanRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!this.securityConfig.passwordEncoder().matches(rawPassword, salesman.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        return salesman;
    }
}
