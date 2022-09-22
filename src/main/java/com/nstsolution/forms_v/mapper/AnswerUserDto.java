package com.nstsolution.forms_v.mapper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerUserDto {


    private Long id;

    private String content; // TODO AS JSON; content is the answer of the user :)
    private Long userId;

    private Long idSurvey;
    private Long idSection;
    private Long idQuestion;

   // private List<QuestionDto> questionEntityList;

    private AttachementDto questionImage; // TODO TO ADD IN DTO MAPPER ....
}
