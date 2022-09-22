package com.nstsolution.forms_v.mapper;

import lombok.Data;

@Data
public class AttachementDto {

    private Long id;

    private byte[] attachementBlob;

    private String nameAttachement;
    private String extension; // .png ...?
    private String attachementObj;

    //private QuestionDto question;

    //private AnswerUserDto answerUser;
}
