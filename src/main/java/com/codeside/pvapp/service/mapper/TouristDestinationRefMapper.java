package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.TouristDestinationRefDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TouristDestinationRef and its DTO TouristDestinationRefDTO.
 */
@Mapper(componentModel = "spring", uses = { PhotoMapper.class})
public interface TouristDestinationRefMapper extends EntityMapper <TouristDestinationRefDTO, TouristDestinationRef> {



}
