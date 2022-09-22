package com.nstsolution.forms_v.service.Iservice;

import com.nstsolution.forms_v.mapper.AnswerUserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserAnswerService {
    Boolean saveUserAnswer(AnswerUserDto answerUserDto);

    ResponseEntity<?> saveAllAnswers(List<AnswerUserDto> answerUserDtos);

    ResponseEntity<?> deleteAll();
}
