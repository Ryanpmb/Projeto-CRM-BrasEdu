package com.brasedu.crm.braseducrm.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Entity()
@Inheritance(strategy= InheritanceType.JOINED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    @Column(name="name", length=150)
    private String name;
    @Column(name="email", unique=true)
    private String email;
    @Column(name="phone", length=15)
    private String phone;
    @Column(name="birthDate")
    private LocalDate birthDate;
}
