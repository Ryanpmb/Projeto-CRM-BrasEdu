package com.brasedu.crm.braseducrm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.dto.request.CreateSalesmanDto;
import com.brasedu.crm.braseducrm.dto.response.ResponseSalesmanDTO;
import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.services.SalesmanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/salesman")
@RequiredArgsConstructor
public class SalesmanController {
    private final SalesmanService salesmanService;

    @PostMapping
    public ResponseEntity<SalesmanEntity> createSalesman(@RequestBody CreateSalesmanDto newSalesmanDto) {
        try {
            SalesmanEntity createdSalesman = salesmanService.store(newSalesmanDto);

            if (createdSalesman == null)
                return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.ok(createdSalesman);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SalesmanEntity> updateCustomer(
            @RequestBody SalesmanEntity newSalesmanInformations,
            @PathVariable String id) {
        SalesmanEntity updatedSalesman = salesmanService.update(newSalesmanInformations, id);

        if (updatedSalesman == null)
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(updatedSalesman);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesmanEntity> getSalesmanById(@PathVariable String id) {
        SalesmanEntity salesman = salesmanService.findById(id);
        if (salesman == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(salesman);
    }

    @GetMapping
    public ResponseEntity<Iterable<ResponseSalesmanDTO>> getAllCustomers() {
        Iterable<ResponseSalesmanDTO> salesmans = salesmanService.findAll();
        return ResponseEntity.ok(salesmans);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesmanById(@PathVariable String id) {
        salesmanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
