package com.brasedu.crm.braseducrm.dto.request;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.SalesStatus;

import lombok.Data;

@Data
public class CreateOpportunityDto {
    public String customerId;
    public String salesmanId;
    public int courseId;
    public SalesStatus salesStatus;
    public LocalDate finished_in;
}
