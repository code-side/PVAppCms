package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.PVAppUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the PVAppUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PVAppUserRepository extends MongoRepository<PVAppUser,String> {
    
}
