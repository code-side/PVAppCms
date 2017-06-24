package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.TouristicInterest;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TouristicInterest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TouristicInterestRepository extends MongoRepository<TouristicInterest,String> {
    
}
