package com.brasedu.crm.braseducrm.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.dto.response.ResponseSaleDTO;
import com.brasedu.crm.braseducrm.services.SaleService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public ResponseEntity<List<ResponseSaleDTO>> findAll(){
        List<ResponseSaleDTO> sales = saleService.findAll();

        return ResponseEntity.ok().body(sales);
    }
    
}
