package com.nstsolution.forms_v.validation;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.StringTokenizer;

public class DateValidatorUsingLocalDate {

    private static DateTimeFormatter dateFormatter;

    public DateValidatorUsingLocalDate(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public static Boolean validateDateFormat(String dateToValdate) {

        Boolean isValid = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //To make strict date format validation
        formatter.setLenient(false);

        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateToValdate);
            if(parsedDate != null) {
                isValid = true;
                return isValid;
            }
        } catch (ParseException e) {
                 isValid= false;
        }
        return isValid;
    }

    public static Boolean  isValidTime(String str){
        Boolean isValid = false;

        StringTokenizer st = new StringTokenizer(str, ":");
        if (st.countTokens() == 3) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm:ss a");
            sdf1.setLenient(false);
            try {
                Date d2 = sdf1.parse(str);
                if(d2!= null ){
                    isValid = true;
                }
            } catch (Exception e) {
                isValid = false;
            }
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            sdf.setLenient(false);
            try {
                Date d1 = sdf.parse(str);
                if(d1 != null){
                    isValid = true;

                }
            } catch (Exception e) {
                isValid = false;
            }
        }

        return isValid;
    }



}
