package com.nstsolution.forms_v.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nstsolution.forms_v.enumeration.QuestionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = QuestionEntity.TABLE_NAME)
@Entity
@Getter
@Setter
public class QuestionEntity {

    public final static String TABLE_NAME = "QUESTION_TB";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private Boolean isRequired;
    private String question; //

    //@Column(name = "options", columnDefinition = "json")
    //@JsonRawValue
    private String options; // option used for multiple choice ....

    @OneToOne // TODO  SEE :))))))))
    private AttachementEntity questionImage; // TODO TO ADD IN DTO MAPPER ....

    @ManyToMany(mappedBy = "questionEntityList",fetch = FetchType.EAGER,cascade ={CascadeType.PERSIST,CascadeType.MERGE}
    )
    private Set<AnswerUserEntity> answerUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private SectionEntity sectionEntity;

}
