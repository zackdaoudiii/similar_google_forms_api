package com.nstsolution.forms_v.mapper;

import lombok.Data;

import java.util.List;

@Data
public class SectionDto {

    private Long id;
    private String sectionTitle;
    private List<QuestionDto> questionList;
    private SurveyDto surveyDto;
}
