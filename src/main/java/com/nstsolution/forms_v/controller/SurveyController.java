package com.nstsolution.forms_v.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.nstsolution.forms_v.mapper.SurveyDto;
import com.nstsolution.forms_v.service.Iservice.ISurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/survey/")
public class SurveyController {

    private final ISurveyService iSurveyService;

    public SurveyController(ISurveyService iSurveyService) {
        this.iSurveyService = iSurveyService;
    }

    @GetMapping
    public ResponseEntity<List<SurveyDto>> find(){
        return iSurveyService.find();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @NotNull SurveyDto surveyDto) throws JsonProcessingException {
        return iSurveyService.create(surveyDto);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        return iSurveyService.deleteAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id){
        return iSurveyService.deleteSurveyById(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        return iSurveyService.findById(id);
    }




}
