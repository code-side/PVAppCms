package com.codeside.pvapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.codeside.pvapp.domain.TouristDestination;

import com.codeside.pvapp.repository.TouristDestinationRepository;
import com.codeside.pvapp.web.rest.util.HeaderUtil;
import com.codeside.pvapp.web.rest.util.PaginationUtil;
import com.codeside.pvapp.service.dto.ReportDTO;
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
public class ReportResource {

    private final Logger log = LoggerFactory.getLogger(TouristDestinationResource.class);

    private static final String ENTITY_NAME = "touristDestination";

    private final TouristDestinationRepository touristDestinationRepository;

    private final TouristDestinationMapper touristDestinationMapper;

    public ReportResource(TouristDestinationRepository touristDestinationRepository, TouristDestinationMapper touristDestinationMapper) {
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
    @PostMapping("/report-destination")
    @Timed
    public ResponseEntity<TouristDestinationDTO> reportTouristDestination(@RequestBody ReportDTO report) throws URISyntaxException {
      boolean exist = false;
      TouristDestination touristDestination = touristDestinationRepository.findOne(report.getIdDestination());

      touristDestination.getReports().add(report.getIdUser());
      for(int i = 0; i < touristDestination.getReports().size(); i++){
        if(report.getIdDestination() == touristDestination.getReports().get(i)){
            exist = true;
            break;
        }
      }

      if(!exist){
        touristDestination = touristDestinationRepository.save(touristDestination);
      }
    TouristDestinationDTO  result = touristDestinationMapper.toDto(touristDestination);

      return ResponseEntity.created(new URI("/api/tourist-destinations/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
    }

}
