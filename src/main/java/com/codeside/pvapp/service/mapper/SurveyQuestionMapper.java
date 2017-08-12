package com.codeside.pvapp.service.mapper;

import com.codeside.pvapp.domain.*;
import com.codeside.pvapp.service.dto.SurveyQuestionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SurveyQuestion and its DTO SurveyQuestionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SurveyQuestionMapper extends EntityMapper <SurveyQuestionDTO, SurveyQuestion> {



}
