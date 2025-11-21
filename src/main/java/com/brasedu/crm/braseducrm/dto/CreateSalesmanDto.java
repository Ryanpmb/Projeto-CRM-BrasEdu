package com.brasedu.crm.braseducrm.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateSalesmanDto {
    public String name;
    public String email;
    public String phone;
    public LocalDate birthDate;
    public String departament;
    public String password;
}
