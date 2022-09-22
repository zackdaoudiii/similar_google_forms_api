package com.nstsolution.forms_v.mapper;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.nstsolution.forms_v.enumeration.QuestionType;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class QuestionDto {

    private Long id;

    private QuestionType questionType;

    private Boolean isRequired;

    private String question; //

    private Set<String> options;

    private AttachementDto questionImage;

    private LinearScaleDto linearScaleDto; // TODO ADD DTO MAPPER :)

    @JsonProperty
    private MultipleANDCheckBoxeChoiceGridDto multipleANDCheckBoxeChoiceGridDto;

    private Set<AnswerUserDto> answerUser;

}
