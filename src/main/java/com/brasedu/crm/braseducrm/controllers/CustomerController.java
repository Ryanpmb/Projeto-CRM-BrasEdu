package com.brasedu.crm.braseducrm.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.dto.CreateCustomerDto;
import com.brasedu.crm.braseducrm.entities.CustomerEntity;
import com.brasedu.crm.braseducrm.services.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController()
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerEntity> createCustomer(@Valid @RequestBody CreateCustomerDto newCustomerDto) {
        try {
            CustomerEntity createdCustomer = customerService.store(newCustomerDto);

            if (createdCustomer == null)
                return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.ok(createdCustomer);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> updateCustomer(
            @Valid @RequestBody CustomerEntity newCustomerInformations,
            @PathVariable String id) {
        CustomerEntity updatedCustomer = customerService.update(newCustomerInformations, id);

        if (updatedCustomer == null)
            return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable String id) {
        CustomerEntity customer = customerService.findById(id);
        if (customer == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customers = customerService.findAll();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
