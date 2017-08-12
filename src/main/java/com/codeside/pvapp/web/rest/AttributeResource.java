package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.Attribute;

import com.codeside.pvapp.repository.AttributeRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.service.dto.AttributeDTO;
import com.codeside.pvapp.service.mapper.AttributeMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Attribute.
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
     * POST  /attributes : Create a new Attribute.
     *
     * @param AttributeDTO the AttributeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new AttributeDTO, or with status 400 (Bad Request) if the Attribute has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/attributes")
    @Timed
    public ResponseEntity<AttributeDTO> createAttributeRef(@RequestBody AttributeDTO attributeDTO) throws URISyntaxException {
        log.debug("REST request to save Attribute : {}", attributeDTO);
        if (attributeDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Attribute cannot already have an ID")).body(null);
        }
        Attribute attribute = attributeMapper.toEntity(attributeDTO);
        attribute = attributeRepository.save(attribute);
        AttributeDTO result = attributeMapper.toDto(attribute);
        return ResponseEntity.created(new URI("/api/attributes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /attributes : Updates an existing Attribute.
     *
     * @param AttributeDTO the AttributeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated AttributeDTO,
     * or with status 400 (Bad Request) if the AttributeDTO is not valid,
     * or with status 500 (Internal Server Error) if the AttributeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/attributes")
    @Timed
    public ResponseEntity<AttributeDTO> updateAttributeRef(@RequestBody AttributeDTO attributeDTO) throws URISyntaxException {
        log.debug("REST request to update Attribute : {}", attributeDTO);
        if (attributeDTO.getId() == null) {
            return createAttributeRef(attributeDTO);
        }
        Attribute attribute = attributeMapper.toEntity(attributeDTO);
        attribute = attributeRepository.save(attribute);
        AttributeDTO result = attributeMapper.toDto(attribute);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, attributeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /attributes : get all the Attributes.
     *
     * @param lang the idiom version of the information
     * @param type the type classification of the attribute
     * @return the ResponseEntity with status 200 (OK) and the list of Attributes in body
     */
    @GetMapping("/attributes")
    @Timed
    public ResponseEntity<List<AttributeDTO>> getAllAttributeRefs(@RequestParam(required=false) String lang, @RequestParam(required=false) String type) {
        log.debug("REST request to get Attributes in lang : " + lang);
        List<Attribute> results;

        if (lang == null && type == null)
        	results = attributeRepository.findAll();
        else if (lang != null && type != null)
        	results = attributeRepository.findAllByIdiomAndType(lang, type);
        else if (lang != null)
        	results = attributeRepository.findAllByIdiom(lang);
        else if (type != null)
        	results = attributeRepository.findAllByType(type);
        else
        	results = new ArrayList<>();

        return new ResponseEntity<>(attributeMapper.toDto(results), HttpStatus.OK);
    }

    /**
     * GET  /attributes/:id : get the "id" Attribute.
     *
     * @param id the id of the AttributeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the AttributeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/attributes/{id}")
    @Timed
    public ResponseEntity<AttributeDTO> getAttributeRef(@PathVariable String id) {
        log.debug("REST request to get Attribute : {}", id);
        Attribute attribute = attributeRepository.findOne(id);
        AttributeDTO attributeDTO = attributeMapper.toDto(attribute);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(attributeDTO));
    }

    /**
     * DELETE  /attributes/:id : delete the "id" Attribute.
     *
     * @param id the id of the AttributeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/attributes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttributeRef(@PathVariable String id) {
        log.debug("REST request to delete Attribute : {}", id);
        attributeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
