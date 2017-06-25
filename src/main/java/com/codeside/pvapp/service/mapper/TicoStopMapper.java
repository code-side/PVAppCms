package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.TicoStopDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TicoStop and its DTO TicoStopDTO.
 */
@Mapper(componentModel = "spring", uses = { ProvinceRefMapper.class })
public interface TicoStopMapper extends EntityMapper <TicoStopDTO, TicoStop> {



}
