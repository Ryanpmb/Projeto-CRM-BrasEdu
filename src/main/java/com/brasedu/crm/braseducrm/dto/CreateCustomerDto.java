package com.brasedu.crm.braseducrm.dto;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.OportunityStatus;
 
public class CreateCustomerDto {
    public String name;
    public String email;
    public String phone;
    public LocalDate birthDate;
    public OportunityStatus leadStatus;
    public String origin;
}
