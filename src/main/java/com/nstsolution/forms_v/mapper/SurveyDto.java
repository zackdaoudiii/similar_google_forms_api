package com.nstsolution.forms_v.mapper;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SurveyDto {

    private Long id;

    private String surveyTitle;
    private String surveySubTitle;
    private String placeHolder;
    //private String surveyLogoUrl;
    private String instruction;
    private String createdBy;
    private Boolean isArchiveFlag;
    private LocalDate archiveDate;

    // TODO private Audit audit;

    private List<SectionDto> sectionList;
}
