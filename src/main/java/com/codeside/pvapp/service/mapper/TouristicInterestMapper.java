package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.TouristicInterestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TouristicInterest and its DTO TouristicInterestDTO.
 */
@Mapper(componentModel = "spring", uses = {ProvinceRefMapper.class, CoordinateMapper.class})
public interface TouristicInterestMapper extends EntityMapper <TouristicInterestDTO, TouristicInterest> {



}
