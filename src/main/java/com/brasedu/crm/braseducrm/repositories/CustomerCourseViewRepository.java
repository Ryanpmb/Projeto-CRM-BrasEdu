package com.brasedu.crm.braseducrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brasedu.crm.braseducrm.dto.query.CustomerCourseViewDTO;

@Repository
public interface CustomerCourseViewRepository extends JpaRepository<CustomerCourseViewDTO, Long> {

    @Query(value = """
        SELECT 
            c.user_id as codigoCliente,
            u.name as nome,
            u.birth_date as dataNascimento,
            co.name as nomeCurso,
            co.hourly_load as cargaHorariaCurso,
            co.value as valorCurso
        FROM vw_customersAndCoursesOfInteress
    """, nativeQuery = true)
    List<Object[]> findAllFromView();
}

