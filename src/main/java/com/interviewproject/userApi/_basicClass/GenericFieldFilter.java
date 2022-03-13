package com.interviewproject.userApi._basicClass;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericFieldFilter<FieldType> {
    private FieldType eq;
    private FieldType ne;
    private FieldType lt;
    private FieldType lte;
    private FieldType gt;
    private FieldType gte;
    private List<FieldType> in;
    private List<FieldType> nin;
    private FieldType c;
    private FieldType ci;
    private FieldType s;
    private FieldType si;
    private FieldType e;
    private FieldType ei;
}
