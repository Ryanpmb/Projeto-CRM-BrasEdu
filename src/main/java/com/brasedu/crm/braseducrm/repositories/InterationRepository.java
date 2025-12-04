package com.brasedu.crm.braseducrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasedu.crm.braseducrm.entities.InterationEntity;

@Repository
public interface InterationRepository extends JpaRepository<InterationEntity, Integer>{

}
