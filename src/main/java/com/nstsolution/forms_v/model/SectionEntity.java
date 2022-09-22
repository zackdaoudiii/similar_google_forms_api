package com.nstsolution.forms_v.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = SectionEntity.TABLE_NAME)
@Entity
@Getter
@Setter
public class SectionEntity {

    public final static String TABLE_NAME = "SECTION_TB";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String sectionTitle;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<QuestionEntity> questionEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    @JsonIgnore
    private SurveyEntity surveyEntity;

}
