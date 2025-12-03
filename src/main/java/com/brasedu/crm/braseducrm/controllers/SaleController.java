package com.brasedu.crm.braseducrm.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.entities.SaleEntity;
import com.brasedu.crm.braseducrm.services.SaleService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleEntity>> findAll(){
        List<SaleEntity> sales = saleService.findAll();

        return ResponseEntity.ok().body(sales);
    }
    
}
