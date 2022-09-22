package com.nstsolution.forms_v.mapper.dtoconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nstsolution.forms_v.mapper.SectionDto;
import com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter.BaseDtoConverter;
import com.nstsolution.forms_v.model.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SectionDtoConverter extends BaseDtoConverter<SectionEntity, SectionDto> {

    private final QuestionDtoConverter questionDtoConverter;
   // private final SurveyDtoMapper surveyDtoMapper;
    @Autowired
    public SectionDtoConverter(QuestionDtoConverter questionDtoConverter) {
        this.questionDtoConverter = questionDtoConverter;
    }


    @Override
    public SectionDto convertToDto(SectionEntity entity) {
        SectionDto sectionDto = new SectionDto();
        sectionDto.setId(entity.getId());
        sectionDto.setSectionTitle(entity.getSectionTitle());
        sectionDto.setQuestionList(
                questionDtoConverter.convertListDtos(entity.getQuestionEntityList()));
     //   sectionDto.setSurveyDto(surveyDtoMapper.convertToDto(entity.getSurveyEntity()));
        return sectionDto;
    }

    @Override
    public SectionEntity convertToEntity(SectionDto dto) throws JsonProcessingException {
        SectionEntity sectionEntity = new SectionEntity();
        sectionEntity.setId(dto.getId());
        sectionEntity.setSectionTitle(dto.getSectionTitle());
        sectionEntity.setQuestionEntityList(
                questionDtoConverter.convertListEntities(dto.getQuestionList()));
        //sectionEntity.setSurveyEntity(surveyDtoMapper.convertToEntity(dto.getSurveyDto()));

        return sectionEntity;
    }

    @Override
    public List<SectionDto> convertListDtos(List<SectionEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()); // TODO make it generics :)
    }

    @Override
    public List<SectionEntity> convertListEntities(List<SectionDto> dto) {
        return dto.stream()
                .map(sectionDto -> {
                    try {
                        return convertToEntity(sectionDto);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


    public Set<SectionEntity> convertSetEntities(List<SectionDto> dto) {
        return dto.stream()
                .map(sectionDto -> {
                    try {
                        return convertToEntity(sectionDto);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toSet());
    }
}
