package com.brasedu.crm.braseducrm.dto.response;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.entities.SalesmanEntity;

import lombok.Data;

@Data
public class ResponseSalesmanDTO {
    String id;
    String name;
    String email;
    String phone;
    LocalDate birthDate;
    String departament;

    public ResponseSalesmanDTO(SalesmanEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.birthDate = entity.getBirthDate();
        this.departament = entity.getDepartament();
    }
}
