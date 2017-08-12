package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.Attribute;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Attribute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AttributeRepository extends MongoRepository<Attribute,String> {

	List<Attribute> findAllByIdiom(String idiom);
	List<Attribute> findAllByType(String type);
	List<Attribute> findAllByIdiomAndType(String idiom, String type);
}
