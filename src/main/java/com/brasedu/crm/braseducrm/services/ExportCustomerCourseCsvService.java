package com.brasedu.crm.braseducrm.services;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExportCustomerCourseCsvService {
    private final JdbcTemplate jdbc;

    public byte[] gerarCsv() {

        String sql = """
                    SELECT
                        [Código do Cliente] AS codigoCliente,
                        [Nome] AS nome,
                        [Data de Nascimento] AS dataNascimento,
                        [Nome do Curso] AS nomeCurso,
                        [Carga Horária do Curso] AS cargaHorariaCurso,
                        [Valor do Curso] AS valorCurso
                    FROM vw_customersAndCoursesOfInteress
                """;

        List<Map<String, Object>> rows = jdbc.queryForList(sql);

        StringBuilder sb = new StringBuilder();

        sb.append("codigoCliente,nome,dataNascimento,nomeCurso,cargaHorariaCurso,valorCurso\n");

        for (Map<String, Object> row : rows) {
            sb.append(row.get("codigoCliente")).append(",");
            sb.append(row.get("nome")).append(",");
            sb.append(row.get("dataNascimento")).append(",");
            sb.append(row.get("nomeCurso")).append(",");
            sb.append(row.get("cargaHorariaCurso")).append(",");
            sb.append(row.get("valorCurso")).append("\n");
        }

        return sb.toString().getBytes();
    }
}
