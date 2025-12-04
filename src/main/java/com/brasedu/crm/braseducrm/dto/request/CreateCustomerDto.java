package com.brasedu.crm.braseducrm.dto.request;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.OportunityStatus;

import lombok.Data;
 
@Data
public class CreateCustomerDto {
    public String name;
    public String email;
    public String phone;
    public LocalDate birthDate;
    public OportunityStatus leadStatus;
    public String origin;
}
