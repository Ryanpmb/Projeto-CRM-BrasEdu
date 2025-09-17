package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.brasedu.crm.braseducrm.enums.OportunityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="custumers")
public class CustumerEntity extends UserEntity {
    @OneToMany(mappedBy="custumers")
    private List<OportunityEntity> oportunities = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name="lead_status")
    private OportunityStatus leadStatus;
    @Column(name="register_date")
    private LocalDate registerDate;
    @Column(name="origin", length=100)
    private String origin;
}
