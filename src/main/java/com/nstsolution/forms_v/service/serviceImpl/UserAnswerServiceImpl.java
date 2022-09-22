package com.nstsolution.forms_v.service.serviceImpl;

import com.nstsolution.forms_v.enumeration.EValidation;
import com.nstsolution.forms_v.mapper.AnswerUserDto;
import com.nstsolution.forms_v.mapper.ResponseDTO;
import com.nstsolution.forms_v.mapper.dtoconverter.AnswerUserDtoConverter;
import com.nstsolution.forms_v.model.AnswerUserEntity;
import com.nstsolution.forms_v.model.QuestionEntity;
import com.nstsolution.forms_v.model.SectionEntity;
import com.nstsolution.forms_v.model.SurveyEntity;
import com.nstsolution.forms_v.repository.AnswerUserRepository;
import com.nstsolution.forms_v.repository.QuestionRepository;
import com.nstsolution.forms_v.repository.SectionRepository;
import com.nstsolution.forms_v.repository.SurveyRepository;
import com.nstsolution.forms_v.service.Iservice.IUserAnswerService;
import com.nstsolution.forms_v.utils.response.ValidationResponse;
import com.nstsolution.forms_v.validation.AnswerUserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAnswerServiceImpl implements IUserAnswerService {

    private final AnswerUserDtoConverter answerUserDtoConverter;

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerUserValidation answerUserValidation;
    private final SectionRepository sectionRepository;
    private final AnswerUserRepository answerUserRepository;


    @Autowired
    public UserAnswerServiceImpl(AnswerUserDtoConverter answerUserDtoConverter, SurveyRepository surveyRepository,
                                 QuestionRepository questionRepository, AnswerUserValidation answerUserValidation,
                                 SectionRepository sectionRepository, AnswerUserRepository answerUserRepository) {
        this.answerUserDtoConverter = answerUserDtoConverter;
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.answerUserValidation = answerUserValidation;
        this.sectionRepository = sectionRepository;
        this.answerUserRepository = answerUserRepository;
    }

    @Override
    public Boolean saveUserAnswer(AnswerUserDto answerUserDto){
        AnswerUserEntity answerUser = answerUserDtoConverter.convertToEntity(answerUserDto);
        ResponseDTO<AnswerUserEntity> userEntityResponseDTO = new ResponseDTO<>();
        if (answerUser.getUserId() == null) {
            return false;//ResponseEntity.badRequest().body("Should be authentified ");
        }
        if(answerUser.getIdSection() == null ){
            return  false;//ResponseEntity.badRequest().body("Id Section should not be null");
        }
        if(answerUser.getIdSurvey() == null ){
            return  false;//ResponseEntity.badRequest().body("Id Section should not be null");
        }
        Optional<SurveyEntity> survey = surveyRepository.findById(answerUser.getIdSurvey());
        Optional<QuestionEntity> questionEntity = questionRepository.findById(answerUser.getIdQuestion());
        Optional<SectionEntity> sectionEntity = sectionRepository.findById(answerUser.getIdSection());

        if (survey != null) {
            survey.get().setSectionEntityList(survey.get()
                    .getSectionEntityList()
                    .stream()
                    .distinct()
                    .collect(Collectors.toList()));

            // add validation of answers
            // i have the type in questionEntity.getTypeQues ...
            // TODO DO VALIDATION BEFORE Insert :)
            ValidationResponse<AnswerUserEntity> answerResponse =  answerUserValidation.AnswerValidation(answerUser,questionEntity.get().getQuestionType());
                if(answerResponse.getEValidation().equals(EValidation.VALIDE)){

                    Set questionSet = new HashSet();
                    questionSet.add(questionEntity.get());
                    answerUser.setQuestionEntityList(questionSet);
                    answerUserRepository.save(answerUser);
                    return true ; //ResponseEntity.ok(answerUserRepository.save(answerUser));

                }else {
                    return false; //ResponseEntity.badRequest().body(answerResponse.getMessageAnnomalies());
                }
        }
        return true;
    }


    @Override
    public ResponseEntity<?> saveAllAnswers(List<AnswerUserDto> answerUserDtos){
        List<AnswerUserEntity> answerUserEntityList = answerUserDtoConverter.convertListEntities(answerUserDtos);
        List<AnswerUserEntity> saveInDb = new ArrayList<>();
        int countInvalid= 0;
        // Validate firts

        for (AnswerUserEntity answerUser: answerUserEntityList) {
            if (answerUser.getUserId() == null) {
                return ResponseEntity.badRequest().body("Should be authentified Answer id ");
            }
            if(answerUser.getIdSection() == null ){
                return  ResponseEntity.badRequest().body("Id Section should not be null");
            }
            if(answerUser.getIdSurvey() == null ){
                return  ResponseEntity.badRequest().body("Id Section should not be null");
            }
            Optional<SurveyEntity> survey = surveyRepository.findById(answerUser.getIdSurvey());
            Optional<QuestionEntity> questionEntity = questionRepository.findById(answerUser.getIdQuestion());

            if (survey != null) {
                survey.get().setSectionEntityList(survey.get()
                        .getSectionEntityList()
                        .stream()
                        .distinct()
                        .collect(Collectors.toList()));
                ValidationResponse<AnswerUserEntity> answerResponse =
                        answerUserValidation.AnswerValidation(answerUser,questionEntity.get().getQuestionType());

                Boolean isRequired =  questionEntity.get().getIsRequired();
                Boolean contentRequired =  answerUserValidation.isRequeriedValidation(answerUser,isRequired);
                if(!contentRequired){
                    countInvalid++;
                }
                if(answerResponse.getEValidation().equals(EValidation.VALIDE)) {

                    Set questionSet = new HashSet();
                    questionSet.add(questionEntity.get());
//                    answerUser.setQuestionEntityList(questionSet);
                    answerUser.setQuestionEntityList(questionSet);
                    saveInDb.add(answerUser);
                }else {
                    // to count invalid ansers
                    answerUser.getContent();
                    countInvalid++;
                }
            }
        }
//        for(int i = 0 ; i < answerUserEntityList.size() ; i++ ){
//            if (answerUserEntityList.get(i).getUserId() == null) {
//                return ResponseEntity.badRequest().body("Should be authentified Answer id ");
//            }
//            if(answerUserEntityList.get(i).getIdSection() == null ){
//                return  ResponseEntity.badRequest().body("Id Section should not be null");
//            }
//            if(answerUserEntityList.get(i).getIdSurvey() == null ){
//                return  ResponseEntity.badRequest().body("Id Section should not be null");
//            }
//            Optional<SurveyEntity> survey = surveyRepository.findById(answerUserDtos.get(i).getIdSurvey());
//            Optional<QuestionEntity> questionEntity = questionRepository.findById(answerUserDtos.get(i).getIdQuestion());
//
//            if (survey != null) {
//                survey.get().setSectionEntityList(survey.get()
//                        .getSectionEntityList()
//                        .stream()
//                        .distinct()
//                        .collect(Collectors.toList()));
//                ValidationResponse<AnswerUserEntity> answerResponse =
//                        answerUserValidation.AnswerValidation(answerUserEntityList.get(i),questionEntity.get().getQuestionType());
//
//                Boolean isRequired =  questionEntity.get().getIsRequired();
//                Boolean contentRequired =  answerUserValidation.isRequeriedValidation(answerUserEntityList.get(i),isRequired);
//                if(!contentRequired){
//                    countInvalid++;
//                }
//                if(answerResponse.getEValidation().equals(EValidation.VALIDE)) {
//
//                    Set questionSet = new HashSet();
//                    questionSet.add(questionEntity.get());
////                    answerUser.setQuestionEntityList(questionSet);
//                    saveInDb.clear();
//                    answerUserEntityList.get(i).setQuestionEntityList(questionSet);
//                    saveInDb.add(answerUserEntityList.get(i));
//                }else {
//                    // to count invalid ansers
//                    countInvalid++;
//                }
//            }
//        }

        if(countInvalid == 0) {
           // answerUserRepository.saveAll(saveInDb);
            saveInDb.stream().forEach(answerUser -> answerUserRepository.save(answerUser));
        }else {
           return ResponseEntity.badRequest().body("Invalid Ansers");
        }

        return ResponseEntity.ok("good");
    }

    @Override
    public ResponseEntity<?> deleteAll(){
        answerUserRepository.deleteAll();
        return ResponseEntity.ok("delete all");
    }


}
