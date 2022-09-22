package com.nstsolution.forms_v.utils.response;

import com.nstsolution.forms_v.enumeration.EValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResponse<E>{

    E entity;
    EValidation eValidation;
    MessageAnnomalie messageAnnomalies;
}


