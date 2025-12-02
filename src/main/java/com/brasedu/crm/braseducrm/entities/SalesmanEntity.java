package com.brasedu.crm.braseducrm.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="salesmans")
@Entity
@PrimaryKeyJoinColumn(name="userId")
public class SalesmanEntity extends UserEntity {
    @OneToMany(mappedBy="salesman")
    @JsonIgnore
    private List<OportunityEntity> oportunities = new ArrayList<>();
    @OneToMany(mappedBy = "salesman")
    private List<SaleEntity> sales = new ArrayList<>();
    @Column(name="departament", length=150)
    private String departament;
    @Column(name="password")
    private String password;
}
