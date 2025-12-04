package com.brasedu.crm.braseducrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasedu.crm.braseducrm.entities.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long>  {
    
}
