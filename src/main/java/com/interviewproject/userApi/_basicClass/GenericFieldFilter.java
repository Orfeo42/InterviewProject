package com.interviewproject.userApi._basicClass;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericFieldFilter<FieldType> {
    private FieldType eq;//EQUAL
    private FieldType ne;//NOT EQUAL
    private FieldType lt;//LESS THAN
    private FieldType lte;//LESS THAN EQUAL
    private FieldType gt;//GREATER THEN
    private FieldType gte;//GREATER THEN EQUAL
    private List<FieldType> in;//IN
    private List<FieldType> nin;//NOT IN
    private FieldType c;//CONTAINS
    private FieldType ci;//CONTAINS IGNORE CASE
    private FieldType s;//START
    private FieldType si;//START IGNORE CASE
    private FieldType e;//END
    private FieldType ei;//END IGNORE CASE
}
