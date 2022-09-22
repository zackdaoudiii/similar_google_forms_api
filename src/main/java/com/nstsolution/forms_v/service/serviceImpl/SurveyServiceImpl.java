package com.nstsolution.forms_v.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nstsolution.forms_v.enumeration.QuestionType;
import com.nstsolution.forms_v.mapper.SurveyDto;
import com.nstsolution.forms_v.mapper.dtoconverter.SurveyDtoMapper;
import com.nstsolution.forms_v.model.QuestionEntity;
import com.nstsolution.forms_v.model.SurveyEntity;
import com.nstsolution.forms_v.repository.SurveyRepository;
import com.nstsolution.forms_v.service.Iservice.ISurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyServiceImpl implements ISurveyService {

    private final SurveyRepository surveyRepository;

    private final SurveyDtoMapper surveyDtoMapper;
    private final char quotes = '"';

    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyDtoMapper surveyDtoMapper) {
        this.surveyRepository = surveyRepository;
        this.surveyDtoMapper = surveyDtoMapper;
    }

    @Override
    public ResponseEntity<List<SurveyDto>> find() {
        return ResponseEntity.ok(surveyDtoMapper.convertListDtos(surveyRepository.findAll()));
    }

    @Override
    public ResponseEntity<?> create(SurveyDto surveyDto) throws JsonProcessingException {
        SurveyEntity survey = surveyDtoMapper.convertToEntity(surveyDto);
            // validate Survey title is required :)
        if(survey.getSurveyTitle().isBlank()){
            return ResponseEntity.badRequest().body("Survey Title is Required !");
        }
        // validate section 1,*  :)
        if(survey.getSectionEntityList().size() == 0){
            return ResponseEntity.badRequest().body("You can create a Survey without sections ");
        }

        survey.getSectionEntityList().stream().forEach(sectionEntity -> {
            sectionEntity.getQuestionEntityList().stream().forEach(questionEntity -> {
                fillQuestionOption(questionEntity.getQuestionType(), questionEntity);
            });
        });

        return ResponseEntity.ok(surveyDtoMapper.convertToDto(surveyRepository.save(survey))); //TODO convert to DTO
    }

    @Override
    public ResponseEntity<?> deleteSurveyById(@NotNull Long id) {
        SurveyEntity survey = surveyRepository.findById(id).get();
        if (survey != null) {
            surveyRepository.delete(survey);
            return ResponseEntity.ok("Survey Id :" + survey.getId() + " deleted sucecc");
        }
        return ResponseEntity.badRequest().body("Survey not found");
    }

    @Override
    public ResponseEntity<?> deleteAll() {
        surveyRepository.deleteAll();
        return ResponseEntity.ok("delete all");
    }

    @Override
    public ResponseEntity<?> findById(@NotNull Long id) {
        Optional<SurveyEntity> survey = surveyRepository.findById(id);

        if (survey != null) {
            survey.get().setSectionEntityList(survey.get()
                    .getSectionEntityList()
                    .stream()
                    .distinct()
                    .collect(Collectors.toList()));

            return ResponseEntity.ok(surveyDtoMapper.convertToDto(survey.get()));
        }
        return ResponseEntity.badRequest().body("Survey not found");
    }

    // TODO CHANGE TO SWITCH CASE
    public void fillQuestionOption(QuestionType questionType, QuestionEntity question) {
        if (QuestionType.SHORT_QUESTION.equals(questionType)
                || QuestionType.PARAGRAPH_QUESTION.equals(questionType)
                || QuestionType.UPLOAD_FILE_QUESTIONS.equals(questionType)
                || QuestionType.DATE_QUESTION.equals(questionType)
                || QuestionType.TIME_QUESTION.equals(questionType) ) {
            setTypedQuestion(question);
        }  else if (QuestionType.MULTIPLE_CHOICE_QUESTION.equals(questionType)
                || QuestionType.CHECKBOXES_QUESTIONS.equals(questionType)
                || QuestionType.DROP_DOWN_LIST_QUESTIONS.equals(questionType) ) {
            setOptions(question);
        } else if (QuestionType.LINEAR_SCALE_QUESTIONS.equals(questionType)) {
            setLinearScaleQuestion(question);
        }
    }

    private void setOptions(QuestionEntity question) {
        List<String> options = new ArrayList<String>(Arrays.asList(question.getOptions().split(",")));

        StringBuilder jsonOptions = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            jsonOptions.append("{");
            jsonOptions.append(quotes);
            jsonOptions.append("option");
            jsonOptions.append(i);
            jsonOptions.append(quotes);
            jsonOptions.append(":");
            jsonOptions.append(quotes);
            jsonOptions.append(options.get(i));
            jsonOptions.append(quotes);
            jsonOptions.append("}");
            jsonOptions.append(",");
        }

        question.setOptions(jsonOptions.toString());
    }

    public void setTypedQuestion(QuestionEntity question) {
        question.setOptions(null);
    }

    public void setLinearScaleQuestion(QuestionEntity question) {

        // TODO TO SEE IMPLEMENT WHAT ?

    }

}
