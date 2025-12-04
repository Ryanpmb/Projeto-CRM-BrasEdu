package com.brasedu.crm.braseducrm.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.dto.request.CreateInterationDTO;
import com.brasedu.crm.braseducrm.dto.response.ResponseInterationDTO;
import com.brasedu.crm.braseducrm.entities.InterationEntity;
import com.brasedu.crm.braseducrm.services.InterationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/interation")
public class InterationController {
    private final InterationService interationService;

    @GetMapping
    public ResponseEntity<List<ResponseInterationDTO>> listAll() {
        List<ResponseInterationDTO> list = interationService.listAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<InterationEntity> include(@Valid @RequestBody CreateInterationDTO interation) {
        InterationEntity newInteration = interationService.include(interation);

        if (newInteration != null) {
            return new ResponseEntity<>(newInteration, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id")
    public ResponseEntity<InterationEntity> edit(@PathVariable int id, @RequestBody InterationEntity interation) {
        InterationEntity updated = interationService.edit(id, interation);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        interationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
