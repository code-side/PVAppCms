package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.EmergencyContactRefDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProvinceRef and its DTO public interface EmergencyContactRefMapper extends EntityMapper <EmergencyContactRefDTO, EmergencyContactRef> {
.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EmergencyContactRefMapper extends EntityMapper <EmergencyContactRefDTO, EmergencyContactRef> {

}
