package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.PaymentMethod;
import com.brasedu.crm.braseducrm.enums.SalesStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class SaleEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name="oportunity_id", unique=true)
    private OportunityEntity oportunity;
    @Column(name="sold_in")
    private LocalDate soldIn;
    @Column(name="final_value")
    private float finalValue;
    @Column(name="payment_method")
    @Enumerated(value=EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name="sales_status")
    @Enumerated(value=EnumType.STRING)
    private SalesStatus salesStatus;
}
