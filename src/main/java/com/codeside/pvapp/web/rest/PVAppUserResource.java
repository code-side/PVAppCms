package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.PVAppUser;

import com.codeside.pvapp.repository.PVAppUserRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.PVAppUserDTO;
import com.codeside.pvapp.service.mapper.PVAppUserMapper;
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

import com.cloudinary.*;
import java.util.Map;
import com.cloudinary.utils.ObjectUtils;
import java.util.HashMap;
import java.io.IOException;


/**
 * REST controller for managing PVAppUser.
 */
@RestController
@RequestMapping("/api")
public class PVAppUserResource {

    private final Logger log = LoggerFactory.getLogger(PVAppUserResource.class);

    private static final String ENTITY_NAME = "pVAppUser";

    private final PVAppUserRepository pVAppUserRepository;

    private final PVAppUserMapper pVAppUserMapper;


    public PVAppUserResource(PVAppUserRepository pVAppUserRepository, PVAppUserMapper pVAppUserMapper) {
        this.pVAppUserRepository = pVAppUserRepository;
        this.pVAppUserMapper = pVAppUserMapper;
    }

    /**
     * POST  /p-v-app-users : Create a new pVAppUser.
     *
     * @param pVAppUserDTO the pVAppUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pVAppUserDTO, or with status 400 (Bad Request) if the pVAppUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/p-v-app-users")
    @Timed
    public ResponseEntity<PVAppUserDTO> createPVAppUser(@RequestBody PVAppUserDTO pVAppUserDTO) throws URISyntaxException {
        log.debug("REST request to save PVAppUser : {}", pVAppUserDTO);
        System.out.println(pVAppUserDTO.toString());
        if (pVAppUserDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pVAppUser cannot already have an ID")).body(null);
        }
        PVAppUser pVAppUser = pVAppUserMapper.toEntity(pVAppUserDTO);
        pVAppUser = pVAppUserRepository.save(pVAppUser);
        PVAppUserDTO result = pVAppUserMapper.toDto(pVAppUser);
        return ResponseEntity.created(new URI("/api/p-v-app-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /p-v-app-users : Updates an existing pVAppUser.
     *
     * @param pVAppUserDTO the pVAppUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pVAppUserDTO,
     * or with status 400 (Bad Request) if the pVAppUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the pVAppUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/p-v-app-users")
    @Timed
    public ResponseEntity<PVAppUserDTO> updatePVAppUser(@RequestBody PVAppUserDTO pVAppUserDTO) throws URISyntaxException {
        log.debug("REST request to update PVAppUser : {}", pVAppUserDTO);
        if (pVAppUserDTO.getId() == null) {
            return createPVAppUser(pVAppUserDTO);
        }
        PVAppUser pVAppUser = pVAppUserMapper.toEntity(pVAppUserDTO);
        pVAppUser = pVAppUserRepository.save(pVAppUser);
        PVAppUserDTO result = pVAppUserMapper.toDto(pVAppUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pVAppUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /p-v-app-users : get all the pVAppUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pVAppUsers in body
     */
    @GetMapping("/p-v-app-users")
    @Timed
    public ResponseEntity<List<PVAppUserDTO>> getAllPVAppUsers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of PVAppUsers");
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
          "cloud_name", "codesidedevs",
          "api_key", "748436656856871",
          "api_secret", "mvN_DfjWnvWgA7ZCaQyUdn4-p4Y"));
        try{
            Map uploadResult = cloudinary.uploader().upload("https://vignette2.wikia.nocookie.net/potcoplayers/images/7/7f/Asdf_Movie.jpg/revision/latest?cb=20110428025933", ObjectUtils.emptyMap());
            System.out.println(uploadResult.get("url"));
        }catch(IOException ex){

        }
        Page<PVAppUser> page = pVAppUserRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/p-v-app-users");
        return new ResponseEntity<>(pVAppUserMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /p-v-app-users/:id : get the "id" pVAppUser.
     *
     * @param id the id of the pVAppUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pVAppUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/p-v-app-users/{id}")
    @Timed
    public ResponseEntity<PVAppUserDTO> getPVAppUser(@PathVariable String id) {
        log.debug("REST request to get PVAppUser : {}", id);
        PVAppUser pVAppUser = pVAppUserRepository.findOne(id);
        PVAppUserDTO pVAppUserDTO = pVAppUserMapper.toDto(pVAppUser);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pVAppUserDTO));
    }

    /**
     * DELETE  /p-v-app-users/:id : delete the "id" pVAppUser.
     *
     * @param id the id of the pVAppUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/p-v-app-users/{id}")
    @Timed
    public ResponseEntity<Void> deletePVAppUser(@PathVariable String id) {
        log.debug("REST request to delete PVAppUser : {}", id);
        pVAppUserRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
