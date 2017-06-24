package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.TouristDestination;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TouristDestination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TouristDestinationRepository extends MongoRepository<TouristDestination,String> {
    
}
