package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.brasedu.crm.braseducrm.enums.SalesStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="oportunities")
@Entity()
public class OportunityEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="custumer_id")
    private CustumerEntity custumer;
    @ManyToOne
    @JoinColumn(name="salesman_id")
    private SalesmanEntity salesman;
    @ManyToOne
    @JoinColumn(name="course_id")
    private int courseId;
    @OneToMany
    private List<InterationEntity> interations = new ArrayList<>();
    @OneToOne(mappedBy="oportunities")
    @Column(name="status")
    @Enumerated(value=EnumType.STRING)
    private SalesStatus salesStatus;
    @Column(name="initiated_at")
    private LocalDate initiatedAt;
    @Column(name="finished_in")
    private LocalDate finishedAt;

}
