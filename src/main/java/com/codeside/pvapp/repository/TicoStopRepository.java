package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.TicoStop;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TicoStop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicoStopRepository extends MongoRepository<TicoStop,String> {
    
}
