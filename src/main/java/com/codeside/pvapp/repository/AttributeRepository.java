package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.AttributeRef;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AttributeRef entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRepository extends MongoRepository<AttributeRef,String> {

}
