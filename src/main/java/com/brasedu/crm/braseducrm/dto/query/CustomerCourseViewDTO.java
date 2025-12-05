package com.brasedu.crm.braseducrm.dto.query;

import lombok.Data;

@Data
public class CustomerCourseViewDTO {

    private String codigoCliente;
    private String nome;
    private String dataNascimento;
    private String nomeCurso;
    private Integer cargaHorariaCurso;
    private Double valorCurso;

}

