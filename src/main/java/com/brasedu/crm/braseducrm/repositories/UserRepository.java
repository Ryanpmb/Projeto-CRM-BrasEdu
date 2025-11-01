package com.brasedu.crm.braseducrm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasedu.crm.braseducrm.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

}
