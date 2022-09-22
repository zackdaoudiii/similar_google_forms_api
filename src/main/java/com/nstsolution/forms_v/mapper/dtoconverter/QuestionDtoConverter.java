package com.nstsolution.forms_v.mapper.dtoconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nstsolution.forms_v.enumeration.QuestionType;
import com.nstsolution.forms_v.mapper.QuestionDto;
import com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter.BaseDtoConverter;
import com.nstsolution.forms_v.model.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDtoConverter extends BaseDtoConverter<QuestionEntity, QuestionDto> {


    private final AttachementDtoConverter attachementDtoConverter;
    private final AnswerUserDtoConverter answerUserDtoConverter;

    @Autowired
    public QuestionDtoConverter(AttachementDtoConverter attachementDtoConverter, AnswerUserDtoConverter answerUserDtoConverter) {
        this.attachementDtoConverter = attachementDtoConverter;
        this.answerUserDtoConverter = answerUserDtoConverter;
    }

    @Override
    public QuestionDto convertToDto(QuestionEntity entity) {
       QuestionDto questionDto = new QuestionDto();
       questionDto.setId(entity.getId());
       questionDto.setQuestionType(entity.getQuestionType());
       questionDto.setIsRequired(entity.getIsRequired());
     questionDto.setOptions(Collections.singleton(entity.getOptions()));

       questionDto.setQuestion(entity.getQuestion());
       questionDto.setQuestionImage(
               entity.getQuestionImage() == null ? null : attachementDtoConverter.convertToDto(entity.getQuestionImage())
       );
        questionDto.setAnswerUser(
                entity.getAnswerUser() == null ? null : answerUserDtoConverter.convertSetDtos(entity.getAnswerUser())
        );// TODO answer converter
        return questionDto;
    }

    @Override
    public QuestionEntity convertToEntity(QuestionDto dto) throws JsonProcessingException {
        QuestionEntity question = new QuestionEntity();
        question.setId(dto.getId());
        question.setQuestionType(dto.getQuestionType());
        question.setIsRequired(dto.getIsRequired());
        if(QuestionType.LINEAR_SCALE_QUESTIONS.equals(dto.getQuestionType())){
           String options = setLinearScaleQuestionType(dto);
           question.setOptions(options == null? null : options);
        } else if(
                QuestionType.CHECKBOXE_GRID_QUESTIONS.equals(dto.getQuestionType())
                || QuestionType.MULTIPLE_CHOICE_GRID_QUESTIONS.equals(dto.getQuestionType())) {
            String json = new ObjectMapper().writeValueAsString(dto.getMultipleANDCheckBoxeChoiceGridDto());
            question.setOptions(json);
        }else if(QuestionType.MULTIPLE_CHOICE_QUESTION.equals(dto.getQuestionType())){
            String json = new ObjectMapper().writeValueAsString(dto.getOptions());
            question.setOptions(json);

        }else {
            question.setOptions(
                    dto.getOptions()==null? null : String.join(",", dto.getOptions()));
        }
        question.setQuestion(dto.getQuestion());
        question.setQuestionImage(
                dto.getQuestionImage()== null ? null : attachementDtoConverter.convertToEntity(dto.getQuestionImage()));
        question.setAnswerUser(
                dto.getAnswerUser() == null ? null : answerUserDtoConverter.convertSetEntities(dto.getAnswerUser())
        );// TODO answer converter
        return question;
    }


    @Override
    public List<QuestionDto> convertListDtos(List<QuestionEntity> entity) {
        return entity.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionEntity> convertListEntities(List<QuestionDto> dto) throws JsonProcessingException {
        List<QuestionEntity>questionEntities = new ArrayList<>();

        for (QuestionDto questionDto:dto) {
            questionEntities.add(convertToEntity(questionDto));
        }
        return questionEntities;
    }

    private String setLinearScaleQuestionType(QuestionDto dto) throws JsonProcessingException {

        String json = new ObjectMapper().writeValueAsString(dto.getLinearScaleDto());
        //question.setOptions(json);

        final char quotes ='"';
        dto.getLinearScaleDto().getLabel1();
        dto.getLinearScaleDto().getFrom();

        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("{");
        stringBuilder.append(dto.getLinearScaleDto().getFrom());
        stringBuilder.append(":");
        stringBuilder.append(dto.getLinearScaleDto().getLabel1() == null ? null : dto.getLinearScaleDto().getLabel1() );
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{");
        stringBuilder.append(dto.getLinearScaleDto().getTo());
        stringBuilder.append(":");
        stringBuilder.append(dto.getLinearScaleDto().getLabel2() == null ? null: dto.getLinearScaleDto().getLabel2());
        stringBuilder.append("}");
        stringBuilder.toString();
        return stringBuilder.toString();
    }

}
