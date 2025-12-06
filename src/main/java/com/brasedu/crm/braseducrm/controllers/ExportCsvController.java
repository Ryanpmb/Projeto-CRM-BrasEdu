package com.brasedu.crm.braseducrm.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brasedu.crm.braseducrm.services.ExportCustomerCourseCsvService;

import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/export")
public class ExportCsvController {
    private final ExportCustomerCourseCsvService exportCustomerCourseCsvService;

    @GetMapping("/customers")
    public ResponseEntity<byte[]> exportCustomerAndCourseCsv() {
        byte[] conteudoCsv = exportCustomerCourseCsvService.gerarCsv();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=customers_courses.csv")
                .body(conteudoCsv);
    }

}
