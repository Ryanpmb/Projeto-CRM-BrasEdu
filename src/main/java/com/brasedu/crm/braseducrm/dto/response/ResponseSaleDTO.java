package com.brasedu.crm.braseducrm.dto.response;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.entities.SaleEntity;
import com.brasedu.crm.braseducrm.enums.PaymentMethod;
import com.brasedu.crm.braseducrm.enums.SalesStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseSaleDTO {
    ResponseOportunityDTO oportunity;
    ResponseSalesmanDTO salesman;
    LocalDate sold_in;
    float finalValue;
    PaymentMethod paymentMethod;
    SalesStatus salesStatus;

    public ResponseSaleDTO(SaleEntity entity){
        this.oportunity = new ResponseOportunityDTO(entity.getOportunity());
        this.salesman = new ResponseSalesmanDTO(entity.getSalesman());
        this.sold_in = entity.getSoldIn();
        this.finalValue = entity.getFinalValue();
        this.paymentMethod = entity.getPaymentMethod();
        this.salesStatus = entity.getSalesStatus();
    }
}
