package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.PhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhotoMapper extends EntityMapper <PhotoDTO, Photo> {



}
