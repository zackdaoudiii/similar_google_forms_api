package com.nstsolution.forms_v.mapper.dtoconverter;

import com.nstsolution.forms_v.mapper.AnswerUserDto;
import com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter.BaseDtoConverter;
import com.nstsolution.forms_v.model.AnswerUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AnswerUserDtoConverter extends BaseDtoConverter<AnswerUserEntity, AnswerUserDto> {

    private final AttachementDtoConverter attachementDtoConverter;

    @Autowired
    public AnswerUserDtoConverter(AttachementDtoConverter attachementDtoConverter) {
        this.attachementDtoConverter = attachementDtoConverter;
    }

    @Override
    public AnswerUserDto convertToDto(AnswerUserEntity entity) {
        AnswerUserDto answerUserDto = new AnswerUserDto();
        answerUserDto.setUserId(entity.getUserId());
        answerUserDto.setContent(entity.getContent());
        answerUserDto.setUserId(entity.getUserId());
        answerUserDto.setIdSection(entity.getIdSection());
        answerUserDto.setIdSurvey(entity.getIdSurvey());
        answerUserDto.setIdQuestion(entity.getIdQuestion());
        answerUserDto.setQuestionImage(
                entity.getQuestionImage() == null? null : attachementDtoConverter.convertToDto(entity.getQuestionImage())
        );
        return answerUserDto;
    }

    @Override
    public AnswerUserEntity convertToEntity(AnswerUserDto dto) {
        AnswerUserEntity answerUser = new AnswerUserEntity();
        answerUser.setUserId(dto.getUserId());
        answerUser.setContent(dto.getContent());
        answerUser.setUserId(dto.getUserId());
        answerUser.setIdSection(dto.getIdSection());
        answerUser.setIdSurvey(dto.getIdSurvey());
        answerUser.setIdQuestion(dto.getIdQuestion());
        answerUser.setQuestionImage(
                dto.getQuestionImage() == null? null : attachementDtoConverter.convertToEntity(dto.getQuestionImage())
        );
        return answerUser;
    }

    @Override
    public List<AnswerUserDto> convertListDtos(List<AnswerUserEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Set<AnswerUserDto> convertSetDtos(Set<AnswerUserEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public List<AnswerUserEntity> convertListEntities(List<AnswerUserDto> dto) {
        return dto.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public Set<AnswerUserEntity> convertSetEntities(Set<AnswerUserDto> dto) {
        return dto.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toSet());

    }

}
