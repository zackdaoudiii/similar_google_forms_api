package com.nstsolution.forms_v.validation;

import com.nstsolution.forms_v.enumeration.EValidation;
import com.nstsolution.forms_v.enumeration.QuestionType;
import com.nstsolution.forms_v.model.AnswerUserEntity;
import com.nstsolution.forms_v.repository.QuestionRepository;
import com.nstsolution.forms_v.utils.response.MessageAnnomalie;
import com.nstsolution.forms_v.utils.response.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerUserValidation {

    private static int shortAnswerLenght = 50;
    private static int paragraphAnswerLenght = 200;

    private final QuestionRepository questionRepository;

    @Autowired
    public AnswerUserValidation(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public ValidationResponse<AnswerUserEntity> AnswerValidation(AnswerUserEntity answerUser, QuestionType questionType){
        ValidationResponse<AnswerUserEntity> answerValidation = new ValidationResponse<>();
        answerValidation.setEValidation(EValidation.VALIDE);
        answerValidation.setEntity(answerUser);

//        Optional<QuestionEntity> questionEntity = questionRepository.findById(answerUser.getIdQuestion());
//        Boolean isRequired = questionEntity.get().getIsRequired();
        switch(questionType) {
            case SHORT_QUESTION:
                //
                    if(answerUser.getContent().length() >= shortAnswerLenght){
                        answerValidation.setEntity(null);
                        answerValidation.setEValidation(EValidation.NOVALID);
                        answerValidation.setMessageAnnomalies(new MessageAnnomalie("Max Lenght "));
                    }

                break;
            case PARAGRAPH_QUESTION:
                    if(answerUser.getContent().length() >= paragraphAnswerLenght){
                        answerValidation.setEntity(null);
                        answerValidation.setEValidation(EValidation.NOVALID);
                        answerValidation.setMessageAnnomalies(new MessageAnnomalie("Max Lenght "));
                    }
                break;

            case MULTIPLE_CHOICE_QUESTION:
            case CHECKBOXES_QUESTIONS:
            case DROP_DOWN_LIST_QUESTIONS:
            case LINEAR_SCALE_QUESTIONS:
            case DATE_QUESTION :
            case TIME_QUESTION:
            case MULTIPLE_CHOICE_GRID_QUESTIONS:
            case CHECKBOXE_GRID_QUESTIONS:
                if(answerUser.getContent().isBlank()){
                    answerValidation.setEntity(null);
                    answerValidation.setEValidation(EValidation.NOVALID);
                    answerValidation.setMessageAnnomalies(new MessageAnnomalie("answer is black ? "));
                }
                break;

            default:
                answerValidation.setEntity(null);
                answerValidation.setEValidation(EValidation.NOVALID);
                answerValidation.setMessageAnnomalies(new MessageAnnomalie("Invalid Type "));
        }
        return answerValidation;
    }



    public Boolean isRequeriedValidation(AnswerUserEntity answerUser, Boolean isRequired){
        if(isRequired){
            if(answerUser.getContent().isBlank()){
                return false;
            }
        }
        return true;
    }
}
