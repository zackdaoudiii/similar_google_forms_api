package com.nstsolution.forms_v.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = SurveyEntity.TABLE_NAME)
@Entity
@Getter
@Setter
public class SurveyEntity {

    public final static String TABLE_NAME = "SURVEY_TB";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String surveyTitle;
    private String surveySubTitle;
    private String placeHolder;
    //private String surveyLogoUrl; // TODO WITH ATTACHEMENT
    private String instruction;
    private String createdBy;
    private Boolean isArchiveFlag;
    private LocalDate archiveDate;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Audit audit;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id")
    private List<SectionEntity> sectionEntityList;
}
