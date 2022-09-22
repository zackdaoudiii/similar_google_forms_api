package com.nstsolution.forms_v.mapper;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@JsonPropertyOrder({ "id", "row"})
public class RowsDto {

    private int id;
    private String row;
}
