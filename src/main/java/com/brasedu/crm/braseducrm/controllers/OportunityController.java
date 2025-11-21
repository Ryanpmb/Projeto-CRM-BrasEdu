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

import com.brasedu.crm.braseducrm.entities.OportunityEntity;
import com.brasedu.crm.braseducrm.services.OportunityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/oportunity")
public class OportunityController {
    private final OportunityService oportunityService;

    @GetMapping
    public ResponseEntity<List<OportunityEntity>> listAll() {
        List<OportunityEntity> list = oportunityService.listAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<OportunityEntity> include(@RequestBody OportunityEntity oportunity) {
        OportunityEntity newOportunity = oportunityService.include(oportunity);

        if (newOportunity != null) {
            return new ResponseEntity<>(newOportunity, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/id")
    public ResponseEntity<OportunityEntity> edit(@PathVariable int id, @RequestBody OportunityEntity oportunity) {
        OportunityEntity updated = oportunityService.edit(id, oportunity);

        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        oportunityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
