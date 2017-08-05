package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.TouristDestination;

import com.codeside.pvapp.repository.TouristDestinationRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.TouristDestinationDTO;
import com.codeside.pvapp.service.mapper.TouristDestinationMapper;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.cloudinary.*;
import java.util.Map;
import com.cloudinary.utils.ObjectUtils;
import java.util.HashMap;
import java.io.IOException;

/**
 * REST controller for managing TouristDestination.
 */
@RestController
@RequestMapping("/api")
public class TouristDestinationResource {

    private final Logger log = LoggerFactory.getLogger(TouristDestinationResource.class);

    private static final String ENTITY_NAME = "touristDestination";

    private final TouristDestinationRepository touristDestinationRepository;

    private final TouristDestinationMapper touristDestinationMapper;

    public TouristDestinationResource(TouristDestinationRepository touristDestinationRepository, TouristDestinationMapper touristDestinationMapper) {
        this.touristDestinationRepository = touristDestinationRepository;
        this.touristDestinationMapper = touristDestinationMapper;
    }

    /**
     * POST  /tourist-destinations : Create a new touristDestination.
     *
     * @param touristDestinationDTO the touristDestinationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new touristDestinationDTO, or with status 400 (Bad Request) if the touristDestination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tourist-destinations")
    @Timed
    public ResponseEntity<TouristDestinationDTO> createTouristDestination(@RequestBody TouristDestinationDTO touristDestinationDTO) throws URISyntaxException {
        log.debug("REST request to save TouristDestination : {}", touristDestinationDTO);
        if (touristDestinationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new touristDestination cannot already have an ID")).body(null);
        }
        TouristDestination touristDestination = touristDestinationMapper.toEntity(touristDestinationDTO);
        touristDestination = touristDestinationRepository.save(touristDestination);
        TouristDestinationDTO result = touristDestinationMapper.toDto(touristDestination);
        return ResponseEntity.created(new URI("/api/tourist-destinations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tourist-destinations : Updates an existing touristDestination.
     *
     * @param touristDestinationDTO the touristDestinationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated touristDestinationDTO,
     * or with status 400 (Bad Request) if the touristDestinationDTO is not valid,
     * or with status 500 (Internal Server Error) if the touristDestinationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tourist-destinations")
    @Timed
    public ResponseEntity<TouristDestinationDTO> updateTouristDestination(@RequestBody TouristDestinationDTO touristDestinationDTO) throws URISyntaxException {
        log.debug("REST request to update TouristDestination : {}", touristDestinationDTO);
        if (touristDestinationDTO.getId() == null) {
            return createTouristDestination(touristDestinationDTO);
        }

        TouristDestination touristDestination = touristDestinationMapper.toEntity(touristDestinationDTO);
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
          "cloud_name", "codesidedevs",
          "api_key", "748436656856871",
          "api_secret", "mvN_DfjWnvWgA7ZCaQyUdn4-p4Y"));
        try{
            Map uploadResult = cloudinary.uploader().upload(touristDestination.getPhotos().get(touristDestination.getPhotos().size() - 1).getUrl(), ObjectUtils.emptyMap());
            System.out.println(uploadResult.get("url"));
            touristDestination.getPhotos().get(touristDestination.getPhotos().size() - 1).setUrl(uploadResult.get("url").toString());
        }catch(IOException ex){}

        TouristDestinationDTO result = touristDestinationMapper.toDto(touristDestinationRepository.save(touristDestination));
        return ResponseEntity.ok()
              .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, touristDestinationDTO.getId().toString()))
              .body(result);
    }

    /**
     * GET  /tourist-destinations : get all the touristDestinations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of touristDestinations in body
     */
    @GetMapping("/tourist-destinations")
    @Timed
    public ResponseEntity<List<TouristDestinationDTO>> getAllTouristDestinations(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TouristDestinations");
        Page<TouristDestination> page = touristDestinationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tourist-destinations");
        return new ResponseEntity<>(touristDestinationMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /tourist-destinations/:id : get the "id" touristDestination.
     *
     * @param id the id of the touristDestinationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the touristDestinationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tourist-destinations/{id}")
    @Timed
    public ResponseEntity<TouristDestinationDTO> getTouristDestination(@PathVariable String id) {
        log.debug("REST request to get TouristDestination : {}", id);
        TouristDestination touristDestination = touristDestinationRepository.findOne(id);
        TouristDestinationDTO touristDestinationDTO = touristDestinationMapper.toDto(touristDestination);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(touristDestinationDTO));
    }

    /**
     * DELETE  /tourist-destinations/:id : delete the "id" touristDestination.
     *
     * @param id the id of the touristDestinationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tourist-destinations/{id}")
    @Timed
    public ResponseEntity<Void> deleteTouristDestination(@PathVariable String id) {
        log.debug("REST request to delete TouristDestination : {}", id);
        touristDestinationRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
