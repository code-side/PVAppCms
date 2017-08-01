package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.AttributeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Attribute and its DTO AttributeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AttributeMapper extends EntityMapper <AttributeDTO, Attribute> {



}
