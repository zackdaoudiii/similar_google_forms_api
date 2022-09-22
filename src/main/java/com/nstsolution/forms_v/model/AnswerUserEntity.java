package com.nstsolution.forms_v.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Table(name = AnswerUserEntity.TABLE_NAME)
@Entity
@Getter
@Setter
public class AnswerUserEntity {

    public final static String TABLE_NAME = "ANSWER_TB";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String content; // TODO AS JSON; content is the answer of the user :)
    private Long userId;

    private Long idSurvey;
    private Long idSection;
    private Long idQuestion;

    @ManyToMany(cascade  =  CascadeType.ALL,
                fetch = FetchType.EAGER)
    private Set<QuestionEntity> questionEntityList;

    @OneToOne // TODO  SEE :))))))))
    private AttachementEntity questionImage; // TODO TO ADD IN DTO MAPPER ....

}
