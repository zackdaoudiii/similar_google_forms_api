package com.nstsolution.forms_v.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class AttachementEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Lob
        @Column(name="blobAttachement")
        private byte[] attachementBlob;

        private String nameAttachement;
        private String extension; // .png ...?
        private String attachementObj; // is section is question ...

        @ManyToOne(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
        private QuestionEntity questionEntity;

        @ManyToOne(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
        private AnswerUserEntity answerUser;
}
