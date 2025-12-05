package com.brasedu.crm.braseducrm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.brasedu.crm.braseducrm.repositories.CustomerCourseViewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExportCustomerCourseCsvService {
    private final CustomerCourseViewRepository repository;

    public byte[] gerarCsv() {

        List<Object[]> data = repository.findAllFromView();

        StringBuilder sb = new StringBuilder();

        sb.append("codigoCliente,nome,dataNascimento,nomeCurso,cargaHorariaCurso,valorCurso\n");

        for (Object[] row : data) {
            sb.append(row[0]).append(",");
            sb.append(row[1]).append(",");
            sb.append(row[2]).append(",");
            sb.append(row[3]).append(",");
            sb.append(row[4]).append(",");
            sb.append(row[5]).append("\n");
        }

        return sb.toString().getBytes();
    }
}
