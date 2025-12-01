package com.brasedu.crm.braseducrm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="courses")
@Entity
public class CourseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy="course", fetch=FetchType.LAZY)
    private  List<OportunityEntity> oportunities = new ArrayList<>();
    @Column(name="name", length=150)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="value")
    private float value;
    @Column(name="hourly_load")
    private int hourlyLoad;
}
