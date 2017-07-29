package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.AttributeRef;

import com.codeside.pvapp.repository.AttributeRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.AttributeRefDTO;
import com.codeside.pvapp.service.mapper.AttributeMapper;
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
 * REST controller for managing AttributeRef.
 */
@RestController
@RequestMapping("/api")
public class AttributeResource {

    private final Logger log = LoggerFactory.getLogger(AttributeResource.class);

    private static final String ENTITY_NAME = "attributes";

    private final AttributeRepository attributeRepository;

    private final AttributeMapper attributeMapper;

    public AttributeResource(AttributeRepository AttributeRepository, AttributeMapper attributeMapper) {
        this.attributeRepository = AttributeRepository;
        this.attributeMapper = attributeMapper;
    }

    /**
     * POST  /AttributeRefs : Create a new AttributeRef.
     *
     * @param AttributeRefDTO the AttributeRefDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new AttributeRefDTO, or with status 400 (Bad Request) if the AttributeRef has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/Attributes")
    @Timed
    public ResponseEntity<AttributeRefDTO> createAttributeRef(@RequestBody AttributeRefDTO AttributeRefDTO) throws URISyntaxException {
        log.debug("REST request to save AttributeRef : {}", AttributeRefDTO);
        if (AttributeRefDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new AttributeRef cannot already have an ID")).body(null);
        }
        AttributeRef AttributeRef = attributeMapper.toEntity(AttributeRefDTO);
        AttributeRef = attributeRepository.save(AttributeRef);
        AttributeRefDTO result = attributeMapper.toDto(AttributeRef);
        return ResponseEntity.created(new URI("/api/Attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /AttributeRefs : Updates an existing AttributeRef.
     *
     * @param AttributeRefDTO the AttributeRefDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated AttributeRefDTO,
     * or with status 400 (Bad Request) if the AttributeRefDTO is not valid,
     * or with status 500 (Internal Server Error) if the AttributeRefDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/Attributes")
    @Timed
    public ResponseEntity<AttributeRefDTO> updateAttributeRef(@RequestBody AttributeRefDTO AttributeRefDTO) throws URISyntaxException {
        log.debug("REST request to update AttributeRef : {}", AttributeRefDTO);
        if (AttributeRefDTO.getId() == null) {
            return createAttributeRef(AttributeRefDTO);
        }
        AttributeRef AttributeRef = attributeMapper.toEntity(AttributeRefDTO);
        AttributeRef = attributeRepository.save(AttributeRef);
        AttributeRefDTO result = attributeMapper.toDto(AttributeRef);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, AttributeRefDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /AttributeRefs : get all the AttributeRefs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of AttributeRefs in body
     */
    @GetMapping("/Attributes")
    @Timed
    public ResponseEntity<List<AttributeRefDTO>> getAllAttributeRefs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of AttributeRefs");
        Page<AttributeRef> page = attributeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/Attributes");
        return new ResponseEntity<>(attributeMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /AttributeRefs/:id : get the "id" AttributeRef.
     *
     * @param id the id of the AttributeRefDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the AttributeRefDTO, or with status 404 (Not Found)
     */
    @GetMapping("/Attributes/{id}")
    @Timed
    public ResponseEntity<AttributeRefDTO> getAttributeRef(@PathVariable String id) {
        log.debug("REST request to get AttributeRef : {}", id);
        AttributeRef AttributeRef = attributeRepository.findOne(id);
        AttributeRefDTO AttributeRefDTO = attributeMapper.toDto(AttributeRef);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(AttributeRefDTO));
    }

    /**
     * DELETE  /AttributeRefs/:id : delete the "id" AttributeRef.
     *
     * @param id the id of the AttributeRefDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/Attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttributeRef(@PathVariable String id) {
        log.debug("REST request to delete AttributeRef : {}", id);
        attributeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
