package com.brasedu.crm.braseducrm.dto.request;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.SalesStatus;

import lombok.Data;

@Data
public class UpdateOpportunityDTO {
    String salesmanId;
    int courseId;
    SalesStatus salesStatus;
    LocalDate finishedAt;
}
