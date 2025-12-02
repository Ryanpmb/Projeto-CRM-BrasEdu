package com.brasedu.crm.braseducrm.dto.response;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.entities.InterationEntity;
import com.brasedu.crm.braseducrm.enums.InterationType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseInterationDTO {
    int id;
    ResponseOportunityDTO oportunity;
    LocalDate interationDate;
    InterationType type;
    String description;

    public ResponseInterationDTO(InterationEntity entity){
        this.id = entity.getId();
        this.oportunity = new ResponseOportunityDTO(entity.getOportunity());
        this.interationDate = entity.getInterationDate();
        this.type = entity.getType();
        this.description = entity.getDescription();
    }
}
