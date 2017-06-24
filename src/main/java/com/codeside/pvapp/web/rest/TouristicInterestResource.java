package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.TouristicInterest;

import com.codeside.pvapp.repository.TouristicInterestRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.TouristicInterestDTO;
import com.codeside.pvapp.service.mapper.TouristicInterestMapper;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TouristicInterest.
 */
@RestController
@RequestMapping("/api")
public class TouristicInterestResource {

    private final Logger log = LoggerFactory.getLogger(TouristicInterestResource.class);

    private static final String ENTITY_NAME = "touristicInterest";

    private final TouristicInterestRepository touristicInterestRepository;

    private final TouristicInterestMapper touristicInterestMapper;

    public TouristicInterestResource(TouristicInterestRepository touristicInterestRepository, TouristicInterestMapper touristicInterestMapper) {
        this.touristicInterestRepository = touristicInterestRepository;
        this.touristicInterestMapper = touristicInterestMapper;
    }

    /**
     * POST  /touristic-interests : Create a new touristicInterest.
     *
     * @param touristicInterestDTO the touristicInterestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new touristicInterestDTO, or with status 400 (Bad Request) if the touristicInterest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/touristic-interests")
    @Timed
    public ResponseEntity<TouristicInterestDTO> createTouristicInterest(@RequestBody TouristicInterestDTO touristicInterestDTO) throws URISyntaxException {
        log.debug("REST request to save TouristicInterest : {}", touristicInterestDTO);
        if (touristicInterestDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new touristicInterest cannot already have an ID")).body(null);
        }
        TouristicInterest touristicInterest = touristicInterestMapper.toEntity(touristicInterestDTO);
        touristicInterest = touristicInterestRepository.save(touristicInterest);
        TouristicInterestDTO result = touristicInterestMapper.toDto(touristicInterest);
        return ResponseEntity.created(new URI("/api/touristic-interests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /touristic-interests : Updates an existing touristicInterest.
     *
     * @param touristicInterestDTO the touristicInterestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated touristicInterestDTO,
     * or with status 400 (Bad Request) if the touristicInterestDTO is not valid,
     * or with status 500 (Internal Server Error) if the touristicInterestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/touristic-interests")
    @Timed
    public ResponseEntity<TouristicInterestDTO> updateTouristicInterest(@RequestBody TouristicInterestDTO touristicInterestDTO) throws URISyntaxException {
        log.debug("REST request to update TouristicInterest : {}", touristicInterestDTO);
        if (touristicInterestDTO.getId() == null) {
            return createTouristicInterest(touristicInterestDTO);
        }
        TouristicInterest touristicInterest = touristicInterestMapper.toEntity(touristicInterestDTO);
        touristicInterest = touristicInterestRepository.save(touristicInterest);
        TouristicInterestDTO result = touristicInterestMapper.toDto(touristicInterest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, touristicInterestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /touristic-interests : get all the touristicInterests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of touristicInterests in body
     */
    @GetMapping("/touristic-interests")
    @Timed
    public ResponseEntity<List<TouristicInterestDTO>> getAllTouristicInterests(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TouristicInterests");
        Page<TouristicInterest> page = touristicInterestRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/touristic-interests");
        return new ResponseEntity<>(touristicInterestMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /touristic-interests/:id : get the "id" touristicInterest.
     *
     * @param id the id of the touristicInterestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the touristicInterestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/touristic-interests/{id}")
    @Timed
    public ResponseEntity<TouristicInterestDTO> getTouristicInterest(@PathVariable String id) {
        log.debug("REST request to get TouristicInterest : {}", id);
        TouristicInterest touristicInterest = touristicInterestRepository.findOne(id);
        TouristicInterestDTO touristicInterestDTO = touristicInterestMapper.toDto(touristicInterest);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(touristicInterestDTO));
    }

    /**
     * DELETE  /touristic-interests/:id : delete the "id" touristicInterest.
     *
     * @param id the id of the touristicInterestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/touristic-interests/{id}")
    @Timed
    public ResponseEntity<Void> deleteTouristicInterest(@PathVariable String id) {
        log.debug("REST request to delete TouristicInterest : {}", id);
        touristicInterestRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
