package com.nstsolution.forms_v.mapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Builder
@Getter
@Setter
@ToString
// TODO DTO :)
public class MultipleANDCheckBoxeChoiceGridDto {

    private Set<RowsDto> rows;
    private Set<ColumnsDto> columns;

}
