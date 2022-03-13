package com.interviewproject.userApi.api.user;

import com.interviewproject.userApi._basicClass.BasicRequest;
import com.interviewproject.userApi._basicClass.GenericFieldFilter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest extends BasicRequest {
    private GenericFieldFilter<String> firstName;
    private GenericFieldFilter<String> lastName;
}
