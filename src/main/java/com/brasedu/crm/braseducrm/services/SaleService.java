package com.brasedu.crm.braseducrm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.entities.SaleEntity;
import com.brasedu.crm.braseducrm.repositories.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;

    public List<SaleEntity> findAll() {
        return saleRepository.findAll();
    }
}
