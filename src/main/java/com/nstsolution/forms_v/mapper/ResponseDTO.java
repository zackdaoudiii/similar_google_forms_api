package com.nstsolution.forms_v.mapper;

import com.nstsolution.forms_v.enumeration.EValidation;
import lombok.Setter;

@Setter
public class ResponseDTO<T>{

    private T entity;
    private String responseValidation;
    private EValidation eValidation;
}
