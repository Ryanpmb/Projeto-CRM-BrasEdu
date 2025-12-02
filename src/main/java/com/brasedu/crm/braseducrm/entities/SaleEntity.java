package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.PaymentMethod;
import com.brasedu.crm.braseducrm.enums.SalesStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sales")
@Entity()
public class SaleEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="oportunity_id", unique=true)
    private OportunityEntity oportunity;
    @ManyToOne()
    @JoinColumn(name = "salesman_id")
    private SalesmanEntity salesman;
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
