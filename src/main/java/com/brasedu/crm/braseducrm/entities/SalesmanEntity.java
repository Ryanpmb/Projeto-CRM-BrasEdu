package com.brasedu.crm.braseducrm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private List<OportunityEntity> oportunities = new ArrayList<>();
    @Column(name="departament", length=150)
    private String departament;
    @Column(name="password")
    private String password;
}
