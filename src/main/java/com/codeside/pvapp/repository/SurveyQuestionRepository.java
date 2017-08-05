package com.codeside.pvapp.repository;

import com.codeside.pvapp.domain.SurveyQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the SurveyQuestion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyQuestionRepository extends MongoRepository<SurveyQuestion,String> {

	List<SurveyQuestion> findAllByIdiom(String idiom);
}
