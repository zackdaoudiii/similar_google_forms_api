package com.nstsolution.forms_v.mapper.dtoconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nstsolution.forms_v.mapper.SurveyDto;
import com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter.BaseDtoConverter;
import com.nstsolution.forms_v.model.SurveyEntity;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SurveyDtoMapper extends BaseDtoConverter<SurveyEntity, SurveyDto> {


    private final SectionDtoConverter sectionDtoConverter;

    public SurveyDtoMapper(SectionDtoConverter sectionDtoConverter) {
        this.sectionDtoConverter = sectionDtoConverter;
    }


    @Override
    public SurveyDto convertToDto(SurveyEntity entity) {
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(entity.getId());
        surveyDto.setSurveyTitle(entity.getSurveyTitle());
        surveyDto.setSurveySubTitle(entity.getSurveySubTitle());
        surveyDto.setPlaceHolder(entity.getPlaceHolder());
        surveyDto.setInstruction(entity.getInstruction());
        surveyDto.setCreatedBy(entity.getCreatedBy());
        surveyDto.setIsArchiveFlag(entity.getIsArchiveFlag());
        surveyDto.setArchiveDate(entity.getArchiveDate());
        //TODO  AUDIT
        surveyDto.setSectionList(sectionDtoConverter.convertListDtos(new ArrayList<>(entity.getSectionEntityList())));
        return surveyDto;
    }

    @Override
    public SurveyEntity convertToEntity(SurveyDto dto) {
        SurveyEntity survey = new SurveyEntity();
        survey.setId(dto.getId());
        survey.setSurveyTitle(dto.getSurveyTitle());
        survey.setSurveySubTitle(dto.getSurveySubTitle());
        survey.setPlaceHolder(dto.getPlaceHolder());
        survey.setInstruction(dto.getInstruction());
        survey.setCreatedBy(dto.getCreatedBy());
        survey.setIsArchiveFlag(dto.getIsArchiveFlag());
        survey.setArchiveDate(dto.getArchiveDate());
        //TODO  AUDIT
        survey.setSectionEntityList(sectionDtoConverter.convertListEntities(dto.getSectionList()));
        return survey;
    }

    @Override
    public List<SurveyDto> convertListDtos(List<SurveyEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyEntity> convertListEntities(List<SurveyDto> dto) throws JsonProcessingException {
        return dto.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
