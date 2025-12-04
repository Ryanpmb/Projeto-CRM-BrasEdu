package com.brasedu.crm.braseducrm.dto.response;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.entities.CustomerEntity;
import com.brasedu.crm.braseducrm.enums.OportunityStatus;

import lombok.Data;

@Data
public class ResponseCustomerDTO {
    String id;
    String name;
    String email;
    String phone;
    LocalDate birthDate;
    OportunityStatus leadStatus;
    String origin;

    public ResponseCustomerDTO(CustomerEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.birthDate = entity.getBirthDate();
        this.leadStatus = entity.getLeadStatus();
        this.origin = entity.getOrigin();
    }
}
