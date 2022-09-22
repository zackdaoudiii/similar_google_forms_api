package com.nstsolution.forms_v.controller;

import com.nstsolution.forms_v.mapper.AnswerUserDto;
import com.nstsolution.forms_v.service.Iservice.IUserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/usersAnwers/")
public class UserAnswerController {


    private final IUserAnswerService iUserAnswerService;

    @Autowired
    public UserAnswerController(IUserAnswerService iUserAnswerService) {
        this.iUserAnswerService = iUserAnswerService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @NotNull AnswerUserDto answerUserDto){
        return ResponseEntity.ok(iUserAnswerService.saveUserAnswer(answerUserDto));
    }

    @PostMapping("saveall")
    public ResponseEntity<?> saveAll(@RequestBody @NotNull List<AnswerUserDto> answerUserDto){
        return ResponseEntity.ok(iUserAnswerService.saveAllAnswers(answerUserDto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        return iUserAnswerService.deleteAll();
    }
}
