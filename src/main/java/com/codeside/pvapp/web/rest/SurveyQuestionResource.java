package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.SurveyQuestion;

import com.codeside.pvapp.repository.SurveyQuestionRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.service.dto.SurveyQuestionDTO;
import com.codeside.pvapp.service.mapper.SurveyQuestionMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SurveyQuestion.
 */
@RestController
@RequestMapping("/api")
public class SurveyQuestionResource {

    private final Logger log = LoggerFactory.getLogger(SurveyQuestionResource.class);

    private static final String ENTITY_NAME = "survey_questions";

    private final SurveyQuestionRepository surveyQuestionRepository;

    private final SurveyQuestionMapper surveyQuestionMapper;

    public SurveyQuestionResource(SurveyQuestionRepository surveyQuestionRepository, SurveyQuestionMapper surveyQuestionMapper) {
        this.surveyQuestionRepository = surveyQuestionRepository;
        this.surveyQuestionMapper = surveyQuestionMapper;
    }

    /**
     * POST  /survey-questions : Create a new SurveyQuestion.
     *
     * @param surveyQuestionDTO the SurveyQuestionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new SurveyQuestionDTO, or with status 400 (Bad Request) if the SurveyQuestion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/survey-questions")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> createSurveyQuestion(@RequestBody SurveyQuestionDTO surveyQuestionDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyQuestion : {}", surveyQuestionDTO);
        if (surveyQuestionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new SurveyQuestion cannot already have an ID")).body(null);
        }
        SurveyQuestion surveyQuestion = surveyQuestionMapper.toEntity(surveyQuestionDTO);
        surveyQuestion = surveyQuestionRepository.save(surveyQuestion);
        SurveyQuestionDTO result = surveyQuestionMapper.toDto(surveyQuestion);
        return ResponseEntity.created(new URI("/api/survey-question/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /survey-questions : Updates an existing SurveyQuestion.
     *
     * @param surveyQuestionDTO the SurveyQuestionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated SurveyQuestionDTO,
     * or with status 400 (Bad Request) if the SurveyQuestionDTO is not valid,
     * or with status 500 (Internal Server Error) if the SurveyQuestionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/survey-questions")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> updateSurveyQuestion(@RequestBody SurveyQuestionDTO surveyQuestionDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyQuestion : {}", surveyQuestionDTO);
        if (surveyQuestionDTO.getId() == null) {
            return createSurveyQuestion(surveyQuestionDTO);
        }
        SurveyQuestion surveyQuestion = surveyQuestionMapper.toEntity(surveyQuestionDTO);
        surveyQuestion = surveyQuestionRepository.save(surveyQuestion);
        SurveyQuestionDTO result = surveyQuestionMapper.toDto(surveyQuestion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, surveyQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /survey-questions : get all the SurveyQuestion.
     *
     * @param lang the idiom version of the information
     * @return the ResponseEntity with status 200 (OK) and the list of SurveyQuestion in body
     */
    @GetMapping("/survey-questions")
    @Timed
    public ResponseEntity<List<SurveyQuestionDTO>> getAllSurveyQuestions(@RequestParam(required=false) String lang) {
        log.debug("REST request to get SurveyQuestion in lang : " + lang);
        List<SurveyQuestion> results = (lang == null ? surveyQuestionRepository.findAll() : surveyQuestionRepository.findAllByIdiom(lang));
        return new ResponseEntity<>(surveyQuestionMapper.toDto(results), HttpStatus.OK);
    }

    /**
     * GET  /survey-questions/:id : get the "id" SurveyQuestion.
     *
     * @param id the id of the SurveyQuestionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the SurveyQuestionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/survey-questions/{id}")
    @Timed
    public ResponseEntity<SurveyQuestionDTO> getSurveyQuestion(@PathVariable String id) {
        log.debug("REST request to get SurveyQuestion : {}", id);
        SurveyQuestion surveyQuestion = surveyQuestionRepository.findOne(id);
        SurveyQuestionDTO SurveyQuestionDTO = surveyQuestionMapper.toDto(surveyQuestion);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(SurveyQuestionDTO));
    }

    /**
     * DELETE  /survey-questions/:id : delete the "id" SurveyQuestion.
     *
     * @param id the id of the SurveyQuestionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/survey-questions/{id}")
    @Timed
    public ResponseEntity<Void> deleteSurveyQuestion(@PathVariable String id) {
        log.debug("REST request to delete SurveyQuestion : {}", id);
        surveyQuestionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
