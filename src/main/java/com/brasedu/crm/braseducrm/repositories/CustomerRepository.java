package com.brasedu.crm.braseducrm.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasedu.crm.braseducrm.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    List<CustomerEntity> findAllByInactiveFalse();
}
