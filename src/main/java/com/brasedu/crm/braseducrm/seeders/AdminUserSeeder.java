package com.brasedu.crm.braseducrm.seeders;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.brasedu.crm.braseducrm.entities.SalesmanEntity;
import com.brasedu.crm.braseducrm.repositories.SalesmanRepository;
import com.brasedu.crm.braseducrm.security.SecurityConfig;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminUserSeeder implements CommandLineRunner {
    private final SalesmanRepository salesmanRepository;
    private final SecurityConfig securityConfig;

    @Override
    public void run(String... args) throws Exception {
        if(salesmanRepository.count() == 0) {
            SalesmanEntity adminUser = new SalesmanEntity();
            adminUser.setName("Admin");
            adminUser.setBirthDate(LocalDate.parse("1990-01-01"));
            adminUser.setPhone("17991568492");
            adminUser.setEmail("admin.user@gmail.com");
            adminUser.setPassword(securityConfig.passwordEncoder().encode("Admin@123"));
            adminUser.setDepartament("Administration");
            salesmanRepository.save(adminUser);
        }
    }
}
