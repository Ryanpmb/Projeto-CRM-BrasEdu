package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;

import com.brasedu.crm.braseducrm.enums.InterationType;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="interations")
@Entity
public class InterationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="oportunity_id")
    private OportunityEntity oportunity;
    @Column(name="interation_date")
    private LocalDate interationDate;
    @Column(name="type")
    @Enumerated(value=EnumType.STRING)
    private InterationType type;
    @Column(name="description")
    private String description;
}
