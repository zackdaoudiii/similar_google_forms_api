package com.nstsolution.forms_v.service.Iservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nstsolution.forms_v.mapper.SurveyDto;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ISurveyService {

    ResponseEntity<List<SurveyDto>> find();

    ResponseEntity<?> create(SurveyDto surveyDto) throws JsonProcessingException;

    ResponseEntity<?> deleteSurveyById(@NotNull Long id);

    ResponseEntity<?> deleteAll();

    ResponseEntity<?> findById(@NotNull Long id);
}
