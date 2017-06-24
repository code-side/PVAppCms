package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.TicoStop;

import com.codeside.pvapp.repository.TicoStopRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.TicoStopDTO;
import com.codeside.pvapp.service.mapper.TicoStopMapper;
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
 * REST controller for managing TicoStop.
 */
@RestController
@RequestMapping("/api")
public class TicoStopResource {

    private final Logger log = LoggerFactory.getLogger(TicoStopResource.class);

    private static final String ENTITY_NAME = "ticoStop";

    private final TicoStopRepository ticoStopRepository;

    private final TicoStopMapper ticoStopMapper;

    public TicoStopResource(TicoStopRepository ticoStopRepository, TicoStopMapper ticoStopMapper) {
        this.ticoStopRepository = ticoStopRepository;
        this.ticoStopMapper = ticoStopMapper;
    }

    /**
     * POST  /tico-stops : Create a new ticoStop.
     *
     * @param ticoStopDTO the ticoStopDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ticoStopDTO, or with status 400 (Bad Request) if the ticoStop has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tico-stops")
    @Timed
    public ResponseEntity<TicoStopDTO> createTicoStop(@RequestBody TicoStopDTO ticoStopDTO) throws URISyntaxException {
        log.debug("REST request to save TicoStop : {}", ticoStopDTO);
        if (ticoStopDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ticoStop cannot already have an ID")).body(null);
        }
        TicoStop ticoStop = ticoStopMapper.toEntity(ticoStopDTO);
        ticoStop = ticoStopRepository.save(ticoStop);
        TicoStopDTO result = ticoStopMapper.toDto(ticoStop);
        return ResponseEntity.created(new URI("/api/tico-stops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tico-stops : Updates an existing ticoStop.
     *
     * @param ticoStopDTO the ticoStopDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ticoStopDTO,
     * or with status 400 (Bad Request) if the ticoStopDTO is not valid,
     * or with status 500 (Internal Server Error) if the ticoStopDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tico-stops")
    @Timed
    public ResponseEntity<TicoStopDTO> updateTicoStop(@RequestBody TicoStopDTO ticoStopDTO) throws URISyntaxException {
        log.debug("REST request to update TicoStop : {}", ticoStopDTO);
        if (ticoStopDTO.getId() == null) {
            return createTicoStop(ticoStopDTO);
        }
        TicoStop ticoStop = ticoStopMapper.toEntity(ticoStopDTO);
        ticoStop = ticoStopRepository.save(ticoStop);
        TicoStopDTO result = ticoStopMapper.toDto(ticoStop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ticoStopDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tico-stops : get all the ticoStops.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ticoStops in body
     */
    @GetMapping("/tico-stops")
    @Timed
    public ResponseEntity<List<TicoStopDTO>> getAllTicoStops(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TicoStops");
        Page<TicoStop> page = ticoStopRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tico-stops");
        return new ResponseEntity<>(ticoStopMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /tico-stops/:id : get the "id" ticoStop.
     *
     * @param id the id of the ticoStopDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ticoStopDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tico-stops/{id}")
    @Timed
    public ResponseEntity<TicoStopDTO> getTicoStop(@PathVariable String id) {
        log.debug("REST request to get TicoStop : {}", id);
        TicoStop ticoStop = ticoStopRepository.findOne(id);
        TicoStopDTO ticoStopDTO = ticoStopMapper.toDto(ticoStop);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ticoStopDTO));
    }

    /**
     * DELETE  /tico-stops/:id : delete the "id" ticoStop.
     *
     * @param id the id of the ticoStopDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tico-stops/{id}")
    @Timed
    public ResponseEntity<Void> deleteTicoStop(@PathVariable String id) {
        log.debug("REST request to delete TicoStop : {}", id);
        ticoStopRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
