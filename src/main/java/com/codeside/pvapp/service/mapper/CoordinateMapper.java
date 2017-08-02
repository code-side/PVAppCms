package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.CoordinateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Coordinate and its DTO CoordinateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoordinateMapper extends EntityMapper <CoordinateDTO, Coordinate> {



}
