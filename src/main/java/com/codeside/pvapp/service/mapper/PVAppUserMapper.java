package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.PVAppUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PVAppUser and its DTO PVAppUserDTO.
 */
 
@Mapper(componentModel = "spring", uses = {PVAppAchievementMapper.class,PVAppFavoriteMapper.class, PhotoMapper.class})
public interface PVAppUserMapper extends EntityMapper <PVAppUserDTO, PVAppUser> {



}
