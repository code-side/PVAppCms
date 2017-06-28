package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.ProvinceRefDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ProvinceRef and its DTO ProvinceRefDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProvinceRefMapper extends EntityMapper <ProvinceRefDTO, ProvinceRef> {

}
