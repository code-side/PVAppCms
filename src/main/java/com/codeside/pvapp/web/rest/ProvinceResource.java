package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.Province;

import com.codeside.pvapp.repository.ProvinceRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.service.dto.ProvinceDTO;
import com.codeside.pvapp.service.mapper.ProvinceMapper;
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
 * REST controller for managing Province.
 */
@RestController
@RequestMapping("/api")
public class ProvinceResource {

    private final Logger log = LoggerFactory.getLogger(ProvinceResource.class);

    private static final String ENTITY_NAME = "province";

    private final ProvinceRepository provinceRepository;

    private final ProvinceMapper provinceMapper;

    public ProvinceResource(ProvinceRepository provinceRepository, ProvinceMapper provinceMapper) {
        this.provinceRepository = provinceRepository;
        this.provinceMapper = provinceMapper;
    }

    /**
     * POST  /provinces : Create a new province.
     *
     * @param provinceDTO the provinceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new provinceDTO, or with status 400 (Bad Request) if the province has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/provinces")
    @Timed
    public ResponseEntity<ProvinceDTO> createProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to save Province : {}", provinceDTO);
        if (provinceDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new province cannot already have an ID")).body(null);
        }
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        ProvinceDTO result = provinceMapper.toDto(province);
        return ResponseEntity.created(new URI("/api/provinces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /provinces : Updates an existing province.
     *
     * @param provinceDTO the provinceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated provinceDTO,
     * or with status 400 (Bad Request) if the provinceDTO is not valid,
     * or with status 500 (Internal Server Error) if the provinceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/provinces")
    @Timed
    public ResponseEntity<ProvinceDTO> updateProvince(@RequestBody ProvinceDTO provinceDTO) throws URISyntaxException {
        log.debug("REST request to update Province : {}", provinceDTO);
        if (provinceDTO.getId() == null) {
            return createProvince(provinceDTO);
        }
        Province province = provinceMapper.toEntity(provinceDTO);
        province = provinceRepository.save(province);
        ProvinceDTO result = provinceMapper.toDto(province);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, provinceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /provinces : get all the provinces.
     *
     * @param lang the idiom version of the information
     * @return the ResponseEntity with status 200 (OK) and the list of provinces in body
     */
    @GetMapping("/provinces")
    @Timed
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces(@RequestParam(required=false) String lang) {
        log.debug("REST request to get a page of Provinces");
        List<Province> results = (lang == null ? provinceRepository.findAll() : provinceRepository.findAllByIdiom(lang));
        return new ResponseEntity<>(provinceMapper.toDto(results), HttpStatus.OK);
    }

    /**
     * GET  /provinces/:id : get the "id" province.
     *
     * @param id the id of the provinceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the provinceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/provinces/{id}")
    @Timed
    public ResponseEntity<ProvinceDTO> getProvince(@PathVariable String id) {
        log.debug("REST request to get Province : {}", id);
        Province province = provinceRepository.findOne(id);
        ProvinceDTO provinceDTO = provinceMapper.toDto(province);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(provinceDTO));
    }

    /**
     * DELETE  /provinces/:id : delete the "id" province.
     *
     * @param id the id of the provinceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/provinces/{id}")
    @Timed
    public ResponseEntity<Void> deleteProvince(@PathVariable String id) {
        log.debug("REST request to delete Province : {}", id);
        provinceRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
