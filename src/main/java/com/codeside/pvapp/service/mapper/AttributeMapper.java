package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.AttributeRefDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AttributeRef and its DTO AttributeRefDTO.
 */
@Mapper(componentModel = "spring", uses = {EmergencyContactRefMapper.class})
public interface AttributeMapper extends EntityMapper <AttributeRefDTO, AttributeRef> {



}
