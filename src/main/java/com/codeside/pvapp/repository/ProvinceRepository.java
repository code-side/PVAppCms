package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Province entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvinceRepository extends MongoRepository<Province,String> {
    
	List<Province> findAllByIdiom(String idiom);
}
