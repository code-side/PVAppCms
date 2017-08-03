package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.TouristDestinationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TouristDestination and its DTO TouristDestinationDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceRefMapper.class, AttributeMapper.class, PhotoMapper.class, ReviewMapper.class, CoordinateMapper.class})
public interface TouristDestinationMapper extends EntityMapper <TouristDestinationDTO, TouristDestination> {



}
