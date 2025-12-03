package com.brasedu.crm.braseducrm.dto.response;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.brasedu.crm.braseducrm.entities.OportunityEntity;
import com.brasedu.crm.braseducrm.enums.PaymentMethod;
import com.brasedu.crm.braseducrm.enums.SalesStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseOportunityDTO {
    int id;
    ResponseCustomerDTO customer;
    ResponseSalesmanDTO salesman;
    ResponseCourseDTO course;
    List<ResponseInterationFromOportunityDTO> interations;
    SalesStatus salesStatus;
    LocalDate initiatedAt;
    LocalDate finishedAt;
    PaymentMethod paymentMethod;

    public ResponseOportunityDTO(OportunityEntity entity){
        this.id = entity.getId();
        this.customer = new ResponseCustomerDTO(entity.getCustomer());
        this.salesman = new ResponseSalesmanDTO(entity.getSalesman());
        this.course = new ResponseCourseDTO(entity.getCourse());
        this.interations = Optional.ofNullable(entity.getInterations())
        .orElse(Collections.emptyList())
        .stream()
        .map(ResponseInterationFromOportunityDTO::new)
        .toList();
        this.salesStatus = entity.getSalesStatus();
        this.initiatedAt = Optional.ofNullable(entity.getInitiatedAt())
        .orElse(null);
        this.finishedAt = Optional.ofNullable(entity.getFinishedAt())
        .orElse(null);
        this.paymentMethod = entity.getPaymentMethod();
    }
}
