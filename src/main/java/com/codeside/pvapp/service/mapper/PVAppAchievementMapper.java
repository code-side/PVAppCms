package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.PVAppUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PVAppUser and its DTO PVAppUserDTO.
 */

@Mapper(componentModel = "spring", uses = {})
public interface PVAppAchievementMapper extends EntityMapper <PVAppUserDTO.Achievement, PVAppUser.Achievement> {



}
